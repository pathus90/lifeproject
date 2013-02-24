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
 * Implementation concerning the {@link LPActivity} Pojo, every server interaction specific to this class lay here.
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
			for(LPActivityUnit activity : project.getActivityUnits()){
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
			
			if(activityUnitToAdd != null){
				activity.updateFromTransit(activityUnitToAdd);
				
				//The Activity list needs to be added by hand because ignored by the updateFromTransit
				Key keyActivity;
				for(String activityKey : activityUnitToAdd.getActivityUnit()){
					keyActivity = KeyFactory.stringToKey(activityKey);
					//We verify that this Key is valid and ignore it if not valid
					//TODO this can cost
					try{
						pm.getObjectById(LPActivity.class, keyActivity);
						activity.addActivity(activityKey);
					}catch(JDOObjectNotFoundException notFound) {
					}
				}
				
			}
			
			
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

	//TODO to be tested
	@Override
	public TransitLPActivity commitActivityUnit(String id,
			String idActivityUnit, String idActivity) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActivity activity = null;
		boolean found = false;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, key);
			
			//Security measures to ensure that both Unit and Activity exists in DB, will be ressource consuming
			int unitIndex = 0;
			for(LPActivityUnit activityUnit : project.getActivityUnits()){
				if(activityUnit.getId().equals(idActivityUnit)){
					for(String activityId : activityUnit.getActivities()){
						if(idActivity.equals(activityId)){
							found = true;
							break;
						}
					}
					break;
				}
				unitIndex++;
			}
			
			//Schema creation part
			if(found){
				
				Key activityKey = KeyFactory.stringToKey(idActivity);
				LPActivity activitySchema = pm.getObjectById(LPActivity.class, activityKey);
				activity = activitySchema.createInstance();
				
				project.addActivity(activity);
				project.removeUnit(unitIndex);
				pm.makePersistent(project);
			}
			
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		//Only return transit if we actually created an activity
		if(found && activity != null)
			return activity.createTransit();
		else
			return null;
		
	}

	//TODO call to be tested
	@Override
	public TransitLPActivity addActivityFromSchema(String id,
			String idOfActivitySchema) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActivity activity = null;
		LPActivity schemaActivity = null;
		
		try{
			Key keyProject = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, keyProject);
			
			//We get the schema, then create an new instance from it
			Key keySchemaActivity = KeyFactory.stringToKey(idOfActivitySchema);
			schemaActivity = pm.getObjectById(LPActivity.class, keySchemaActivity);
			System.out.println("Size of schema : " + schemaActivity.getTasks().size());
			activity = schemaActivity.createInstance();

			//Adding the new activity to project
			project.addActivity(activity);
			
			pm.makePersistent(project);
			
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		//Only return transit if we actually created an activity
		if(activity != null)
			return activity.createTransit();
		else
			return null;
	}

	@Override
	public TransitLPActivityUnit addActivityUnitFromSchema(String id,
			String idOfActivityUnitSchema) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActivityUnit activityUnit = null;
		LPActivityUnit schemaActivityUnit = null;
		
		try{
			Key keyProject = KeyFactory.stringToKey(id);
			LPProject project = pm.getObjectById(LPProject.class, keyProject);
			
			//We get the schema, then create an new instance from it
			Key keySchemaActivityUnit = KeyFactory.stringToKey(idOfActivityUnitSchema);
			schemaActivityUnit = pm.getObjectById(LPActivityUnit.class, keySchemaActivityUnit);
			activityUnit = schemaActivityUnit.createInstance();
			
			//Adding the new activity to project
			project.addUnit(activityUnit);
			
			pm.makePersistent(project);
			
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		//Only return transit if we actually created an activity
		if(activityUnit != null)
			return activityUnit.createTransit();
		else
			return null;
	}
	
}
