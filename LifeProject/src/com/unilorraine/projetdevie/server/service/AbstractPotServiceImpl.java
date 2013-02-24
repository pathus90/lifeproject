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

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.unilorraine.projetdevie.client.service.helperinterfaces.IPotService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.AbstractLPPot;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitHelper;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public abstract class AbstractPotServiceImpl<T extends ITransitEntity, S extends AbstractLPEntity<T>> extends CRUDRemoteService<TransitLPPot<T>> implements IPotService<T,S> {
	
	@Override
	public T addStoredEntitiy(String id) {
		return addStoredEntitiy(id, null);
	}

	@Override
	public T addStoredEntitiy(String id, T entity) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPPot<T,S> pot = null;
		S storedEntity = null;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			pot = pm.getObjectById(getLPEntityClass(), key);
			
			storedEntity = pot.getInstanceOfStoredEntity();
			
			if(entity != null)
				storedEntity.updateFromTransit(entity);
			
			if(!pot.addEntity(storedEntity))
				return null;

			pm.makePersistent(pot);
				
			
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		if(storedEntity != null)
			return storedEntity.createTransit();
		else
			return null;
	}

	@Override
	public List<TransitLPPot<T>> getTransitPotsByLink(String link) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		List<AbstractLPPot<T,S>> pots = null;
		List<TransitLPPot<T>> transitPots = null;
		
		try{
			
			Query q = pm.newQuery(getLPEntityClass());
			q.setFilter("linkedEntities == link");
			q.declareParameters(String.class.getName() + " link");

			pots = (List<AbstractLPPot<T,S>>) q.execute(link);
			
			TransitHelper<TransitLPPot<T>, AbstractLPPot<T,S>> helper = new TransitHelper<TransitLPPot<T>, AbstractLPPot<T,S>>();
			
			if(pots != null)
				transitPots = helper.createTransitEntities(pots);
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return transitPots != null? transitPots : null;
	}

	@Override
	public List<String> getAllLinks(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPPot<T,S> pot = null;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			pot = pm.getObjectById(getLPEntityClass(), key);
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		if(pot != null)
			return pot.getAllLink();
		return null;
	}

	@Override
	public boolean addLink(String id, String link) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPPot<T,S> pot = null;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			pot = pm.getObjectById(getLPEntityClass(), key);
			
			boolean response =  pot.addLink(link);
			
			pm.makePersistent(pot);
			
			return response;
			
		}catch(JDOObjectNotFoundException notFound) {
			return false;
		}finally{
			pm.close();
		}
	}

	@Override
	public boolean removeLink(String id, String link) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPPot<T,S> pot = null;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			pot = pm.getObjectById(getLPEntityClass(), key);
			
			boolean response =  pot.removeLink(link);
			
			pm.makePersistent(pot);
			
			return response;
			
		}catch(JDOObjectNotFoundException notFound) {
			return false;
		}finally{
			pm.close();
		}
	}

}
