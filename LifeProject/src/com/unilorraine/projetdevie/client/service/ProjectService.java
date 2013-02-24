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
package com.unilorraine.projetdevie.client.service;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unilorraine.projetdevie.client.service.helperinterfaces.ICrudService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;

/**
 * Service rpc concernant les pojos LPProjet. Toutes les fonctions nécéssaires à la communication avec le serveur se trouve ici.
 * @author Christophe
 *
 */
@RemoteServiceRelativePath("ProjectService")
public interface ProjectService extends RemoteService , ICrudService<TransitLPProject>{
	
	/**
	 * Get all the activities for the project passed as argument.
	 * 
	 * @param id the LPProject from which we want to get the activities
	 * @return a list of the activity transit objects for that project
	 */
	public ArrayList<TransitLPActivity> getAllActivities(String id);
	
	/**
	 * Method to get all the activities for one specific category
	 * @param id the id of the LPProject from which the activities come
	 * @param category the id of the wanted category
	 * @return a list of all the activity transit objects linked with the given category
	 */
	public ArrayList<TransitLPActivity> getActivitesForCategory(String id, String category);
	
	/**
	 * Add a new activity return the transit entity of this activity
	 * @param id the LPProject from which we want to get the activities 
	 * @return the transit object of the newly created object, or null if error
	 */
	public TransitLPActivity addActivity(String id);
	
	/**
	 * Add a new activity created from the transit to the project and return the transit entity of this activity
	 * @param id the LPProject where the activity should be added 
	 * @param activitytoAdd the transit object from which the new activity will be created from
	 * @return the transit object of the newly created object, or null if error
	 */
	public TransitLPActivity addActivity(String id, TransitLPActivity activityToAdd);
	
	
	/**
	 * Method to get all the activity-unit for one specific category
	 * @param id the id of the LPProject from which the activity-unit come
	 * @param category the id of the wanted category
	 * @return a list of all the activity-unit transit objects linked with the given category
	 */
	public ArrayList<TransitLPActivityUnit> getActivityUnitsForCategory(String id, String category);
	
	/**
	 * Get all the activity-units for the project passed as argument.
	 * 
	 * @param id the LPProject from which we want to get the activity-units
	 * @return a list of the activity-units transit objects for that project
	 */
	public ArrayList<TransitLPActivityUnit> getAllActivityUnits(String id);
	
	/**
	 * Add a new activity-unit created from the transit to the project and return the transit entity of this activity-unit
	 * @param id the LPProject from which we want to get the activity-unit 
	 * @param activityUnitToAdd the transit object from which the new activity-unit will be created from
	 * @return the transit object of the newly created object, or null if error
	 */
	public TransitLPActivityUnit addActivityUnits(String id, TransitLPActivityUnit activityUnitToAdd);
	
	/**
	 * Add a new activity-unit created from the transit to the project and return the transit entity of this activity-unit
	 * @param id the LPProject from which we want to get the activity-unit 
	 * @return the transit object of the newly created object, or null if error
	 */
	public TransitLPActivityUnit addActivityUnit(String id);
	
	/**
	 * Commit, meaning validate, the activity-unit by selecting the given activity from it.
	 * This method will destroy the activity-unit and instantiate a new LPActivity based on the schema chosen (copy). 
	 * @param id the id of the Project
	 * @param idActivityUnit the id of the Unit
	 * @param idActivity the id of the Activity in unit
	 * @return a transit object for newly the created activity
	 */
	public TransitLPActivity commitActivityUnit(String id, String idActivityUnit, String idActivity);
	
	/**
	 * Add a activity created from a schema activity to the project and return the transit entity of this activity
	 * @param id the LPProject where the activity should be added 
	 * @param idOfActivitySchema the id of the activity from which the new activity will be created from
	 * @return the transit object of the newly created object, or null if error (in retrieving schema or creating the activity from it)
	 */
	public TransitLPActivity addActivityFromSchema(String id, String idOfActivitySchema);
	
	/**
	 * Add an activity-unit created from a schema activity-unit to the project and return the transit entity of this activity-unit
	 * @param id the LPProject where the activity-unit should be added 
	 * @param idOfActivityUnitSchema the id of the activity-unit from which the new activity-unit will be created from
	 * @return the transit object of the newly created object, or null if error (in retrieving schema or creating the activity-unit from it)
	 */
	public TransitLPActivityUnit addActivityUnitFromSchema(String id, String idOfActivityUnitSchema);

	
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static ProjectServiceAsync instance;
		public static ProjectServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(ProjectService.class);
			}
			return instance;
		}
	}
}
