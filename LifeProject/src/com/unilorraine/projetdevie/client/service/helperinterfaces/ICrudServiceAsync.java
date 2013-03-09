package com.unilorraine.projetdevie.client.service.helperinterfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

public interface ICrudServiceAsync<T extends ITransitEntity> {

	void updateEntity(T transitentity, AsyncCallback<T> callback);
	
	void createEntity(T transitentity, AsyncCallback<T> callback);
	
	void createEntity(AsyncCallback<T> callback);
	
	void deleteEntity(T transitentity, AsyncCallback<T> callback);
	
	void deleteEntity(String id, AsyncCallback<T> callback);
	
	void readEntity(String id, AsyncCallback<T> callback);
	
	void updateEntities(List<T> transitEntities, AsyncCallback<List<T>> calback);
	
	void deleteEntities(List<T> transitEntities, AsyncCallback<List<T>> calback);
	
	void deleteEntitiesFromId(List<String> entitiesId, AsyncCallback<List<T>> calback);
	
	void readEntities(List<String> ids,  AsyncCallback<List<T>> calback);
}
