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
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;

/**
 * Remote Service interface for an activity service
 * @author Christophe
 *
 */
@RemoteServiceRelativePath("ActivityService")
public interface ActivityService extends RemoteService, ICrudService<TransitLPActivity>{
	
	/**
	 * Add a new empty task to the activity and return the transit entity of this task
	 * @param id the id of the Activity for which a new task is to be created
	 * @return the transit entity off the newly added task
	 */
	TransitLPTask addTask(String id);
	
	/**
	 * Add a new task created from the transit to the activity and return the transit entity of this task
	 * @param id the id of the Activity for which a new task is to be created
	 * @param transitTask the task data from which the new task should be created
	 * @return the transit entity off the newly added task
	 */
	TransitLPTask addTask(String id, TransitLPTask transitTask);
	
	/**
	 * Add a new task created from a task schema to the activity and return the transit entity of this task
	 * @param id the id of the Activity for which a new task is to be created
	 * @param taskSchemaId the task schema from which the new task should be created
	 * @return the transit entity off the newly added task, or null if either the activity or schema could not be found or instantiated
	 */
	TransitLPTask addTaskFromSchema(String id, String taskSchemaId);
	
	/**
	 * Get all the transit Task linked with the activity.
	 * @param id the id of the activity you want to inspect
	 * @return an ArrayList of the tasks in a transit form 
	 */
	ArrayList<TransitLPTask> getAllTasks(String id);
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static ActivityServiceAsync instance;
		public static ActivityServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(ActivityService.class);
			}
			return instance;
		}
	}
}
