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

import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.unilorraine.projetdevie.client.service.UserService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.accountentities.LPUser;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.AbstractLPPot;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends CRUDRemoteService<TransitLPUser> implements UserService {

	@Override
	public TransitLPUser getUserForEmail(String email) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPUser user = null;

		try{
			
			Query q = pm.newQuery(getLPEntityClass());
			q.setFilter("email == emailParam");
			q.declareParameters(String.class.getName() + " emailParam");

			List<LPUser> users = (List<LPUser>) q.execute(email);
			
			if(users.size() != 0)
				return users.get(0).createTransit();
			
			
		}catch(JDOObjectNotFoundException notFound) {
			System.err.println("Not found");
			return null;
		}catch(Exception e){
			System.err.println("general error");
		}
		finally{
			pm.close();
		}

		return null;
	}

	@Override
	public TransitLPProject createBuildingProjectFromTransit(String id,
			TransitLPProject buildingProject) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPUser user = null;
		LPProject project = null;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			user = pm.getObjectById(getLPEntityClass(), key);
			
			project = new LPProject();
			project.updateFromTransit(buildingProject);

			user.setBuildingProject(project);
			
			pm.makePersistent(user);
			
		}catch(JDOObjectNotFoundException notFound) {
			System.err.println("Not found : user with id " + id);
			return null;
		}catch(Exception e){
			System.err.println("general error");
		}finally{
			pm.close();
		}
		
		if(project != null)
			return project.createTransit();
		else
			return null;
	}

	@Override
	protected AbstractLPEntity<TransitLPUser> getLPEntity() {
		return new LPUser();
	}

	@Override
	protected Class getLPEntityClass() {
		return LPUser.class;
	}
}
