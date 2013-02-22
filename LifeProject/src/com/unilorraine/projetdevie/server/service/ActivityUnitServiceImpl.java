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

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.unilorraine.projetdevie.client.service.ActivityUnitService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivityUnit;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ActivityUnitServiceImpl extends CRUDRemoteService<TransitLPActivityUnit> implements ActivityUnitService {

	@Override
	protected AbstractLPEntity<TransitLPActivityUnit> getLPEntity() {
		return new LPActivityUnit();
	}

	@Override
	protected Class getLPEntityClass() {
		return LPActivityUnit.class;
	}

	//TODO Needs to be tested
	@Override
	public TransitLPActivityUnit addActivity(String activityUnitId,
			String activitySchemaId) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActivityUnit unit = null;
		
		try{
			Key key = KeyFactory.stringToKey(activityUnitId);
			unit = pm.getObjectById(LPActivityUnit.class, key);
			
			//We check if the schema exists in the db, would throw an error if not
			Key keyActivity = KeyFactory.stringToKey(activitySchemaId);
			pm.getObjectById(LPActivity.class, keyActivity);
			
			if(unit.addActivity(activitySchemaId))
				pm.makePersistent(unit);
			else
				return null;

		}catch(JDOObjectNotFoundException notFound) {
			System.err.println("Not found");
			return null;
		}finally{
			pm.close();
		}
		
		if(unit != null)
			return unit.createTransit();
		else
			return null;
	}

	//TODO Needs to be tested
	@Override
	public TransitLPActivityUnit removeActivity(String activityUnitId,
			String activitySchemaId) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		LPActivityUnit unit = null;
		
		try{
			Key key = KeyFactory.stringToKey(activityUnitId);
			unit = pm.getObjectById(LPActivityUnit.class, key);
			
			//We check if the schema exists in the db, would throw an error if not
			Key keyActivity = KeyFactory.stringToKey(activitySchemaId);
			pm.getObjectById(LPActivity.class, keyActivity);
			
			if(unit.removeActivity(activitySchemaId))
				pm.makePersistent(unit);
			else
				return null;

		}catch(JDOObjectNotFoundException notFound) {
			System.err.println("Not found");
			return null;
		}finally{
			pm.close();
		}
		
		if(unit != null)
			return unit.createTransit();
		else
			return null;
	}
}
