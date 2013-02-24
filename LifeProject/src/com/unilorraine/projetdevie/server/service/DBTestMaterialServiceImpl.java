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
package com.unilorraine.projetdevie.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.unilorraine.projetdevie.client.service.DBTestMaterialService;
import com.unilorraine.projetdevie.client.shared.jdoentities.IDBEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.AbstractLPPot;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.LPPotActivity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author Christophe
 *
 */
public class DBTestMaterialServiceImpl extends RemoteServiceServlet implements DBTestMaterialService {

	public static final String CAT_SPORT = "Sport";
	public static final String CAT_SOCIAL = "Social";
	public static final String CAT_MUSIC = "Musique";
	
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
	
	private TransitLPActivityUnit unitSport;
	
	
	private TransitLPProject projet;
	
	
	
	@Override
	public void populateDB() {
		
		
		createCategories();
		
		createSchemaTasks();
		
		createActivityPot();
		
//		createSchemaActivities();
				
		createProject();
		
	}

	private void createActivityPot() {
		PotActivityServiceImpl potService = new PotActivityServiceImpl();
		ActivityServiceImpl implActivity = new ActivityServiceImpl();
		
		activityPot = potService.createEntity();
		
		actFootball = potService.addStoredEntitiy(activityPot.getId(), new TransitLPActivity("", "Football", "apprend à jouer au foot!", "http://openclipart.org/image/128px/svg_to_png/32491/football.png", true, 0, catSport.getId(), ""));
		implActivity.addTaskFromSchema(actFootball.getId(), taskRules.getId());
		implActivity.addTaskFromSchema(actFootball.getId(), taskPlay.getId());
		
		actBasketball = potService.addStoredEntitiy(activityPot.getId(), new TransitLPActivity("", "Basketball", "apprend à jouer au basketball!", "http://openclipart.org/image/128px/svg_to_png/4667/Gioppino_Basketball.png", true, 0, catSport.getId(), ""));
		implActivity.addTaskFromSchema(actBasketball.getId(), taskRules.getId());
		implActivity.addTaskFromSchema(actBasketball.getId(), taskPlay.getId());
		
		actBread = potService.addStoredEntitiy(activityPot.getId(), new TransitLPActivity("", "Aller à la boulangerie", "Apprend à acheter ton pain", "http://openclipart.org/image/128px/svg_to_png/16974/jean_victor_balin_bread.png", true, 0, catSocial.getId(), ""));
		implActivity.addTaskFromSchema(actBread.getId(), taskGreet.getId());
		implActivity.addTaskFromSchema(actFootball.getId(), taskMoney.getId());
		
	}

	private void createSchemaTasks() {
		TaskServiceImpl impl = new TaskServiceImpl();
		
		taskRules = impl.createEntity(new TransitLPTask("", "Apprendre les règles", "Il faut apprendre les règle pour jouer", "http://openclipart.org/image/128px/svg_to_png/97465/study.png", true, 0, ""));
		taskPlay = impl.createEntity(new TransitLPTask("", "Apprendre à jouer", "Il temps d'apprendre à jouer pour de vrai!", "http://openclipart.org/image/128px/svg_to_png/721/johnny_automatic_playing_ball.png", true, 0, ""));
		taskGreet = impl.createEntity(new TransitLPTask("", "Dire bonjours", "Dire bonjours à la boulangère", "http://openclipart.org/image/128px/svg_to_png/167186/Hello.png", true, 0, ""));
		taskMoney = impl.createEntity(new TransitLPTask("", "Gérer son argent", "Donner le bon montant pour acheter son pain", "http://openclipart.org/image/128px/svg_to_png/73795/1279537209.png", true, 0, ""));
		
		
	}

	/**
	 * Create a test Project
	 */
	private void createProject() {
		ProjectServiceImpl impl = new ProjectServiceImpl();

		projet = impl.createEntity(new TransitLPProject("", "Projet de Paul", "Description pour le projet de Paul", "", true, 0, ""));
		
		//We put the two sports activities in a ActivityUnit to choose between them
		ArrayList<String> activityList = new ArrayList<String>();
		activityList.add(actFootball.getId());
		activityList.add(actBasketball.getId());
		
		unitSport = impl.addActivityUnits(projet.getId(), new TransitLPActivityUnit("", activityList, catSport.getId()));
		
		//impl.commitActivityUnit(projet.getId(), unitSport.getId(), actFootball.getId());
		TransitLPActivity actIBread = impl.addActivityFromSchema(projet.getId(), actBread.getId());
		
		//implActivity.deleteEntity(actIBread);
		
	}

	/**
	 * Create some schema activities
	 */
	private void createSchemaActivities() {
		ActivityServiceImpl implActivity = new ActivityServiceImpl();
		
		actFootball = implActivity.createEntity(new TransitLPActivity("", "Football", "apprend à jouer au foot!", "http://openclipart.org/image/128px/svg_to_png/32491/football.png", true, 0, catSport.getId(), ""));
		implActivity.addTaskFromSchema(actFootball.getId(), taskRules.getId());
		implActivity.addTaskFromSchema(actFootball.getId(), taskPlay.getId());
		
		actBasketball = implActivity.createEntity(new TransitLPActivity("", "Basketball", "apprend à jouer au basketball!", "http://openclipart.org/image/128px/svg_to_png/4667/Gioppino_Basketball.png", true, 0, catSport.getId(), ""));
		implActivity.addTaskFromSchema(actBasketball.getId(), taskRules.getId());
		implActivity.addTaskFromSchema(actBasketball.getId(), taskPlay.getId());
		
		actBread = implActivity.createEntity(new TransitLPActivity("", "Aller à la boulangerie", "Apprend à acheter ton pain", "http://openclipart.org/image/128px/svg_to_png/16974/jean_victor_balin_bread.png", true, 0, catSocial.getId(), ""));
		implActivity.addTaskFromSchema(actBread.getId(), taskGreet.getId());
		implActivity.addTaskFromSchema(actFootball.getId(), taskMoney.getId());
		
	}

	/**
	 * Populate the DB with Categories
	 */
	private void createCategories(){
		CategoryServiceImpl impl = new CategoryServiceImpl();
		//Sport
		catSport = impl.createEntity(new TransitLPCategory("", CAT_SPORT, "Une description pour la categorie sport", "http://openclipart.org/image/128px/svg_to_png/100777/sport_girl.png"));
		//Social
		catSocial = impl.createEntity(new TransitLPCategory("", CAT_SOCIAL, "Une description pour la categorie social", "http://openclipart.org/image/128px/svg_to_png/101989/population.png"));
		//Musique
		catMusic = impl.createEntity(new TransitLPCategory("", CAT_MUSIC, "Une description pour la categorie musique", "http://openclipart.org/image/128px/svg_to_png/3308/zeimusu_Recorder_and_music.png"));
	}
}
