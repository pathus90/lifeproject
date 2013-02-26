/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.unilorraine.projetdevie.server.service.init;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import org.eclipse.jdt.internal.compiler.flow.InsideSubRoutineFlowContext;

import com.unilorraine.projetdevie.client.service.init.DBInitService;
import com.unilorraine.projetdevie.client.service.pots.PotUserGroupService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.accountentities.LPActor;
import com.unilorraine.projetdevie.client.shared.jdoentities.accountentities.LPInstitution;
import com.unilorraine.projetdevie.client.shared.jdoentities.accountentities.LPUser;
import com.unilorraine.projetdevie.client.shared.jdoentities.accountentities.LPUserGroup;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUserGroup;
import com.unilorraine.projetdevie.server.service.ActivityServiceImpl;
import com.unilorraine.projetdevie.server.service.CategoryServiceImpl;
import com.unilorraine.projetdevie.server.service.PMF;
import com.unilorraine.projetdevie.server.service.ProjectServiceImpl;
import com.unilorraine.projetdevie.server.service.TaskServiceImpl;
import com.unilorraine.projetdevie.server.service.pots.PotActivityServiceImpl;
import com.unilorraine.projetdevie.server.service.pots.PotCategoryServiceImpl;
import com.unilorraine.projetdevie.server.service.pots.PotUserGroupServiceImpl;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Documentation at {@link DBInitService}
 * @author Christophe
 *
 */
public class DBInitServiceImpl extends RemoteServiceServlet implements DBInitService {

	public static final String ACTOR_GROUP = "actor"; 
	
	private static final String CAT_SPORT = "Sport";
	private static final String CAT_SOCIAL = "Social";
	private static final String CAT_MUSIC = "Musique";
	
	private TransitLPPot<TransitLPActivity> activityPot;
	
	private TransitLPCategory catSport;
	private TransitLPCategory catSocial;
	private TransitLPCategory catMusic;
	
	private TransitLPActivity actFootball;
	private TransitLPActivity actBasketball;
	private TransitLPActivity actBread;
	
	private TransitLPTask taskRules;
	private TransitLPTask taskPlay;
	private TransitLPTask taskGreet;
	private TransitLPTask taskMoney;

	@Override
	public List<ITransitEntity> initMethod() {
		
		TransitLPInstitution institution = createInstitution();
		
		String groupID = createGroups(institution.getId());
		
		TransitLPUser user = createUser(institution.getId());
		
		TransitLPActor actor = createActor(groupID, institution.getId(), user.getId());
		
		createCategories(institution.getId());
		
		//This should also be done by pots because we will not use the tasks, we will do it this way
		//But normally you put them directly in the shema activities so they are linked with make a own Task pot
		createSchemaTasks();
		
		//Create the pot of activities
		createActivityPot(institution.getId());
				
		//finally create an empty project for our user
		createProject(user.getId());
		
		List<ITransitEntity> list = new ArrayList<ITransitEntity>();
		list.add(institution);
		list.add(actor);
		return list;
	}

	private String createGroups(String institutionID) {
		PotUserGroupService groupService = new PotUserGroupServiceImpl();
		
		//create the pot using the RPC, no id needed and no entries passed because they would ignored.
		TransitLPPot<TransitLPUserGroup> groupTransitPot = groupService.createEntity(new TransitLPPot<TransitLPUserGroup>("", "User Group pot", "The user rights pot", null));
		
		//Now adding a new user group to the pot by using a transit object
		TransitLPUserGroup userGroup= groupService.addStoredEntitiy(groupTransitPot.getId(), 
				new TransitLPUserGroup("", ACTOR_GROUP, "they have the right to edit a life project they are associated with"));
		
		//Now linking the pot with the institution
		groupService.addLink(groupTransitPot.getId(), institutionID);
		
		//We return the id for convenience we could have fetched it again
		return userGroup.getId();
		
		
		
	}

	private TransitLPActor createActor(String groupID, String institutionID, String userID) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActor lp = null;
		
		try{
			 lp = new LPActor("Craig", "David", "craigdavid@googlemail.com", "I'll teach them all!", "http://www.magixl.com/cliparts/jpg/music_pop/craig_david.jpg");
			
			//Adding the institution 
			lp.addInstution(institutionID);
			
			//Adding the user
			lp.addUser(userID);
			
			//adding the group
			lp.addGroup(groupID);
			
			//finally save it
			pm.makePersistent(lp);

		}finally{
			pm.close();
			
		}
		if(lp != null)
			return lp.createTransit();
		else
			return null;
		
	}

	private TransitLPUser createUser(String institutionID) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPUser lp = null;
		
		try{
			 lp = new LPUser("Guetta", "David", "davidguetta@googlemail.com", "I'm David Guetta, a way to gloryfied <musiscian> for my talent", "http://www.magixl.com/cliparts/jpg/music_pop/david_guetta.jpg");
			lp.addInstution(institutionID);
			
			pm.makePersistent(lp);

		}finally{
			pm.close();
			
		}
		if(lp != null)
			return lp.createTransit();
		else
			return null;
		
	}

	private TransitLPInstitution createInstitution() {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPInstitution lp = null;
		
		try{
			 lp = new LPInstitution("Sainte Marie des Prés", "45, rue du Paradis perdu", "stmarie@googlemail.com", "067859453");
			
			pm.makePersistent(lp);

		}finally{
			pm.close();
			
		}
		if(lp != null)
			return lp.createTransit();
		else
			return null;
		
	}
	
	private void createActivityPot(String institutionID) {
		
		PotActivityServiceImpl potService = new PotActivityServiceImpl();
		
		//Used for adding task schemas to the activities
		ActivityServiceImpl implActivity = new ActivityServiceImpl();
		
		activityPot = potService.createEntity(new TransitLPPot<TransitLPActivity>("", "Activities available for our institution", "", null));
		
		//The same process here as for the categories, the activities need to be created by the pot service because the pot will by their father in the tree hierarchy
		
		actFootball = potService.addStoredEntitiy(activityPot.getId(), new TransitLPActivity("", "Football", "apprend à jouer au foot!", "http://openclipart.org/image/128px/svg_to_png/32491/football.png", true, 0, catSport.getId(), ""));
		//adding task schemas to the activity
		implActivity.addTaskFromSchema(actFootball.getId(), taskRules.getId());
		implActivity.addTaskFromSchema(actFootball.getId(), taskPlay.getId());
		
		actBasketball = potService.addStoredEntitiy(activityPot.getId(), new TransitLPActivity("", "Basketball", "apprend à jouer au basketball!", "http://openclipart.org/image/128px/svg_to_png/4667/Gioppino_Basketball.png", true, 0, catSport.getId(), ""));
		//adding task schemas to the activity
		implActivity.addTaskFromSchema(actBasketball.getId(), taskRules.getId());
		implActivity.addTaskFromSchema(actBasketball.getId(), taskPlay.getId());
		
		actBread = potService.addStoredEntitiy(activityPot.getId(), new TransitLPActivity("", "Aller à la boulangerie", "Apprend à acheter ton pain", "http://openclipart.org/image/128px/svg_to_png/16974/jean_victor_balin_bread.png", true, 0, catSocial.getId(), ""));
		//adding task schemas to the activity
		implActivity.addTaskFromSchema(actBread.getId(), taskGreet.getId());
		implActivity.addTaskFromSchema(actFootball.getId(), taskMoney.getId());
		
		//finally linking with the institution
		potService.addLink(activityPot.getId(), institutionID);
		
	}

	private void createSchemaTasks() {
		
		//This will create Task with no father. You don't want that in reality, cause it would mean having a lot of not linked classes running around on the root level
		
		TaskServiceImpl impl = new TaskServiceImpl();
		
		taskRules = impl.createEntity(new TransitLPTask("", "Apprendre les règles", "Il faut apprendre les règle pour jouer", "http://openclipart.org/image/128px/svg_to_png/97465/study.png", true, 0, ""));
		taskPlay = impl.createEntity(new TransitLPTask("", "Apprendre à jouer", "Il temps d'apprendre à jouer pour de vrai!", "http://openclipart.org/image/128px/svg_to_png/721/johnny_automatic_playing_ball.png", true, 0, ""));
		taskGreet = impl.createEntity(new TransitLPTask("", "Dire bonjours", "Dire bonjours à la boulangère", "http://openclipart.org/image/128px/svg_to_png/167186/Hello.png", true, 0, ""));
		taskMoney = impl.createEntity(new TransitLPTask("", "Gérer son argent", "Donner le bon montant pour acheter son pain", "http://openclipart.org/image/128px/svg_to_png/73795/1279537209.png", true, 0, ""));
		
		
	}

	/**
	 * Create a test Project
	 */
	private void createProject(String userID) {
		
		//again no rpc so we need to do it by hand
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPUser lp = null;
		System.out.println("User ID " + userID);
		try{
			Key key = KeyFactory.stringToKey(userID);
			lp = pm.getObjectById(LPUser.class, key);
			
			//We create a new empty project as project that needs to be build
			lp.setBuildingProject(new LPProject("David's Project", "I will learn how to use more then 3 fingers to make music this year!", "", true));
			
			//And make the thing persistent
			pm.makePersistent(lp);

		}finally{
			pm.close();
			
		}
		
	}

	/**
	 * Populate the DB with Categories
	 */
	private void createCategories(String institutionID){
		
		//Using the category pot service
		PotCategoryServiceImpl potService = new PotCategoryServiceImpl();
		
		//create the pot
		TransitLPPot<TransitLPCategory> catPot = potService.createEntity(new TransitLPPot<TransitLPCategory>("", "Categories available for our institution", "", null));
		
		//Filling the pot with categories
		//Sport
		catSport = potService.addStoredEntitiy(catPot.getId(), new TransitLPCategory("", CAT_SPORT, "Une description pour la categorie sport", "http://openclipart.org/image/128px/svg_to_png/100777/sport_girl.png"));
		//Social
		catSocial =  potService.addStoredEntitiy(catPot.getId(), new TransitLPCategory("", CAT_SOCIAL, "Une description pour la categorie social", "http://openclipart.org/image/128px/svg_to_png/101989/population.png"));
		//Musique
		catMusic =  potService.addStoredEntitiy(catPot.getId(), new TransitLPCategory("", CAT_MUSIC, "Une description pour la categorie musique", "http://openclipart.org/image/128px/svg_to_png/3308/zeimusu_Recorder_and_music.png"));
		
		//finally linking the pot to the institution
		potService.addLink(catPot.getId(), institutionID);
	}
}
