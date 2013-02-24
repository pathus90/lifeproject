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
package com.unilorraine.projetdevie.client.service.init;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;

/**
 * This service is an initialization service for the DB. 
 * It will create a project for a user and an actor linked to that user.
 * It will also create some categories in a categories pot linked to the institution, 
 * as well as some schema activities in an activity pot also linked to the institution.
 * This objects can be used to test the interface as long as no admin tools are provided. 
 * This Service should be seen as a basic tutorial on how the communication with the server works 
 * (expect for the account classes because no rpc is currently available we will do it by hand).
 * We will even create a user group for Actor called "autor" meaning has the rights to autor a life project. 
 * The user group will be fetched from a user group pot linked to the institution.
 * @author Christophe
 *
 */
@RemoteServiceRelativePath("DBInitService")
public interface DBInitService extends RemoteService {
	
	/**
	 * This method will initialize the db but not remove the old object! 
	 * You have to do that by hand by deleting the /war/WEB-INF/appengine-generated/local_db.bin file.
	 * Then call this method to setup the db.
	 */
	public void initMethod();
	
	/**
	 * The institution this scenario play in. The institution will be linked with the category an activity pot you need. 
	 * So use the Id provided by this transit to get them from their respective potservice.
	 * @return the institution transit for this scenario
	 */
	public TransitLPInstitution getInstitution();
	
	/**
	 * The user transit object, he is holding the project, so use the id provided by this transit and the projectservice to get the building project.
	 * @return the transit object of the user for this scenario
	 */
	public TransitLPUser getUser();
	
	/**
	 * get the transit object for the actor, meaning the person responsible for the actor, he will be part of the "autor" usergroup
	 * @return transit of the responsible actor for our user
	 */
	public TransitLPActor getActor();
	
	
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static DBInitServiceAsync instance;
		public static DBInitServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(DBInitService.class);
			}
			return instance;
		}
	}
}
