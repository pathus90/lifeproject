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

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;

public interface ProjectServiceAsync extends ICrudServiceAsync<TransitLPProject>{

	void getAllActivities(String id,
			AsyncCallback<ArrayList<TransitLPActivity>> callback);

	void getActivitesForCategory(String id, String category,
			AsyncCallback<ArrayList<TransitLPActivity>> callback);

	void addActivity(String id, TransitLPActivity activityToAdd,
			AsyncCallback<TransitLPActivity> callback);

	void addActivity(String id, AsyncCallback<TransitLPActivity> callback);

	void getActivityUnitsForCategory(String id, String category,
			AsyncCallback<ArrayList<TransitLPActivityUnit>> callback);

	void getAllActivityUnits(String id,
			AsyncCallback<ArrayList<TransitLPActivityUnit>> callback);

	void addActivityUnits(String id, TransitLPActivityUnit activityUnitToAdd,
			AsyncCallback<TransitLPActivityUnit> callback);

	void addActivityUnit(String id,
			AsyncCallback<TransitLPActivityUnit> callback);

	void commitActivityUnit(String id, String idActivityUnit,
			String idActivity, AsyncCallback<TransitLPActivity> callback);

	void addActivityFromSchema(String id, String idOfActivitySchema,
			AsyncCallback<TransitLPActivity> callback);

	void addActivityUnitFromSchema(String id, String idOfActivityUnitSchema,
			AsyncCallback<TransitLPActivityUnit> callback);
}
