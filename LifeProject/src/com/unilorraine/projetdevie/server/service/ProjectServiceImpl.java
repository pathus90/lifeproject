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

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.unilorraine.projetdevie.client.service.ProjectService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivityUnit;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Implementation du service rpc concernant les pojos LPProjet. Toutes les fonctions nécéssaires à la communication avec le serveur se trouve ici.
 * @author Christophe
 *
 */
public class ProjectServiceImpl extends CRUDRemoteService<TransitLPProject> implements ProjectService {

	@Override
	protected AbstractLPEntity<TransitLPProject> getLPEntity() {
		return new LPProject();
	}

	@Override
	public ArrayList<TransitLPActivity> getAllActivities(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		ArrayList<TransitLPActivity> list = new ArrayList<TransitLPActivity>();
		try{
			Key key = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, key);
			for(LPActivity activity : project.getActivities()){
				list.add(activity.createTransit());
			}
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return list;
	}

	@Override
	public ArrayList<TransitLPActivity> getActivitesForCategory(String id,
			String category) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		ArrayList<TransitLPActivity> list = new ArrayList<TransitLPActivity>();
		try{
			Key key = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, key);
			for(LPActivity activity : project.getActivitiesForCategory(category)){
				list.add(activity.createTransit());
			}
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return list;
	}

	@Override
	public TransitLPActivity addActivity(String id) {
		return addActivity(id, null);
	}

	@Override
	public TransitLPActivity addActivity(String id,
			TransitLPActivity activityToAdd) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActivity activity = new LPActivity();
		try{
			Key key = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, key);
			
			if(activityToAdd != null)
				activity.updateFromTransit(activityToAdd);
			project.addActivity(activity);
			pm.makePersistent(project);
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return activity.createTransit();
	}

	@Override
	public ArrayList<TransitLPActivityUnit> getActivityUnitsForCategory(
			String id, String category) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		ArrayList<TransitLPActivityUnit> list = new ArrayList<TransitLPActivityUnit>();
		try{
			Key key = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, key);
			for(LPActivityUnit activity : project.getActivityUnitsForCategory(category)){
				list.add(activity.createTransit());
			}
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return list;
	}

	@Override
	public ArrayList<TransitLPActivityUnit> getAllActivityUnits(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		ArrayList<TransitLPActivityUnit> list = new ArrayList<TransitLPActivityUnit>();
		try{
			Key key = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, key);
			for(LPActivityUnit activity : project.getChoiceUnits()){
				list.add(activity.createTransit());
			}
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return list;
	}

	@Override
	public TransitLPActivityUnit addActivityUnits(String id,
			TransitLPActivityUnit activityUnitToAdd) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActivityUnit activity = new LPActivityUnit();
		try{
			Key key = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, key);
			
			if(activityUnitToAdd != null)
				activity.updateFromTransit(activityUnitToAdd);
			project.addUnit(activity);
			pm.makePersistent(project);
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return activity.createTransit();
	}

	@Override
	public TransitLPActivityUnit addActivityUnit(String id) {
		return addActivityUnits(id, null);
	}

	@Override
	protected Class<LPProject> getLPEntityClass() {
		return LPProject.class;
	}

	@Override
	public TransitLPActivity commitActivityUnit(String id,
			String idActivityUnit, String idActivity) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
