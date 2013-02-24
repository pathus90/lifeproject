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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unilorraine.projetdevie.client.service.helperinterfaces.ICrudService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;

@RemoteServiceRelativePath("ActivityUnitService")
public interface ActivityUnitService extends RemoteService, ICrudService<TransitLPActivityUnit> {

	/**
	 * Add an activity to the Unit. This activity can only be added as Schema, meaning, it has to already exist in the database and no hard copy will be made.
	 * It is not possible to add more than the maximum accepted by the unit see LPActivityUnit.MAX_ITEM .
	 * @param activityUnitId the id of the activity unit 
	 * @param activitySchemaId the activity schema that should be added to the unit, null if an error occurred
	 * @return the transit object of the modified activity unit
	 */
	public TransitLPActivityUnit addActivity(String activityUnitId, String activitySchemaId);
	
	/**
	 * Removes an activity from unit. This will not affect in anyway the existence of the Activity to be removed in the Database
	 * @param activityUnitId the id of the activity unit 
	 * @param activitySchemaId the activity schema that should be removed from the unit
	 * @return the transit object of the modified activity unit, null if an error occurred
	 */
	public TransitLPActivityUnit removeActivity(String activityUnitId, String activitySchemaId);
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static ActivityUnitServiceAsync instance;
		public static ActivityUnitServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(ActivityUnitService.class);
			}
			return instance;
		}
	}
}
