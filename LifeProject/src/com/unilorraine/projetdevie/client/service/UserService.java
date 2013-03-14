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
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;

//TODO Fast implementation pre-presentation, needs to be rewritten
@RemoteServiceRelativePath("UserService")
public interface UserService extends RemoteService, ICrudService<TransitLPUser> {
	
	/**
	 * Get the user by his email.
	 * @param email the user email
	 * @return the transit for the user or null if not in the DB
	 */
	public TransitLPUser getUserForEmail(String email);
	
	/**
	 * Create a new building Project for given the user id
	 * @param id the user is for which we need a building project.
	 * @param buildingProject
	 * @return the transit object for the new building project.
	 */
	public TransitLPProject createBuildingProjectFromTransit(String id, TransitLPProject buildingProject);
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static UserServiceAsync instance;
		public static UserServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(UserService.class);
			}
			return instance;
		}
	}
}
