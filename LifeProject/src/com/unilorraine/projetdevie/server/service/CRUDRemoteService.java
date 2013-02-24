package com.unilorraine.projetdevie.server.service;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.unilorraine.projetdevie.client.service.helperinterfaces.ICrudService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * Abstract implementation of the ICrudService. This will be used for every service that performs basic CRUD operation with a AbstractLPEntity implementation.
 * You need to pass the implementation through the getLPEntity class and the rest will be handled for you.
 * @author Christophe
 *
 * @param <T> the transit that will be created by the Transitable db entity. It has to be implementation of the ITransitEntity
 */
public abstract class CRUDRemoteService<T extends ITransitEntity> extends RemoteServiceServlet implements ICrudService<T>{
	
	/**
	 * This method returns the implementation of the AbstractLPEntity needed for this Service
	 * @return
	 */
	protected abstract AbstractLPEntity<T> getLPEntity();
	
	/**
	 * This method returns the Class of the pojo to work with. 
	 * This is to avoid having to create a new object every time we just need the class for the persistence 
	 * @return
	 */
	protected abstract Class getLPEntityClass();

	@Override
	public T createEntity(T transitEntity) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPEntity<T> lp = null;
		
		try{
			 lp = getLPEntity();


			if(transitEntity != null)
				lp.updateFromTransit(transitEntity);
			
			pm.makePersistent(lp);
			
			
			
		}finally{
			pm.close();
			
		}
		if(lp != null)
			return lp.createTransit();
		else
			return null;
	}
	
	@Override
	public T createEntity() {
		
		return createEntity(null);
		
	}

	@Override
	public T updateEntity(T transitEntity) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPEntity<T> lp = null;
		
		try{
			Key key = KeyFactory.stringToKey(transitEntity.getId());
			lp = pm.getObjectById(getLPEntityClass(), key);
			
			if(transitEntity != null)
				lp.updateFromTransit(transitEntity);
			
			pm.makePersistent(lp);
			
			
		}catch(JDOObjectNotFoundException notFound) {
			System.out.println("Not found");
			return null;
		}finally{
			pm.close();
		}
		
		if(lp != null)
			return lp.createTransit();
		else
			return null;
	}

	@Override
	public T deleteEntity(T transitEntity) {
		return deleteEntity(transitEntity.getId());
	}

	@Override
	public T deleteEntity(String id) {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPEntity<T> lp = null;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			lp = pm.getObjectById(getLPEntityClass(), key);
			
			T transit = lp.createTransit();
			
			pm.deletePersistent(lp);
			
			return transit;
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
	}

	//TODO this really NEEDS TO BE TESTED
	@Override
	public T readEntity(String id) {
		
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		AbstractLPEntity<T> lp = null;
		
		try{
			Key key = KeyFactory.stringToKey(id);
			lp = pm.getObjectById(getLPEntityClass(), key);
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		if(lp != null)
			return lp.createTransit();
		else
			return null;
	}
	
	

}
