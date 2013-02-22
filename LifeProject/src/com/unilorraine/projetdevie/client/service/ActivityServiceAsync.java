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

import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ActivityServiceAsync extends ICrudServiceAsync<TransitLPActivity>{
	
	/**
	 * Get all the transit Task linked with the activity.
	 * @param id the id of the activity you want to inspect
	 * @param callback the callback to return an ArrayList of the tasks in a transit form 
	 */
	void getAllTasks(String id, AsyncCallback<ArrayList<TransitLPTask>> callback);

	/**
	 * Add a new empty task to the activity and return the transit entity of this task
	 * @param id the id of the Activity for which a new task is to be created
	 * @return the transit entity off the newly added task
	 */
	void addTask(String id, AsyncCallback<TransitLPTask> callback);

	/**
	 * Add a new task created from the transit to the activity and return the transit entity of this task
	 * @param id the id of the Activity for which a new task is to be created
	 * @param transitTask the task data from which the new task should be created
	 * @return the transit entity off the newly added task
	 */
	void addTask(String id, TransitLPTask transitTask,
			AsyncCallback<TransitLPTask> callback);

	/**
	 * Add a new task created from a task schema to the activity and return the transit entity of this task
	 * @param id the id of the Activity for which a new task is to be created
	 * @param taskSchemaId the task schema from which the new task should be created
	 * @return the transit entity off the newly added task, or null if either the activity or schema could not be found or instantiated
	 */
	void addTaskFromSchema(String id, String taskSchemaId,
			AsyncCallback<TransitLPTask> callback);
}
