package com.unilorraine.projetdevie.client.service.helperinterfaces;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;

public interface IPotServiceAsync<T extends ITransitEntity, S extends AbstractLPEntity<T>> extends ICrudServiceAsync<TransitLPPot<T>> {

	void addStoredEntitiy(String id, AsyncCallback<T> callback);

	void getTransitPotsByLink(String link, AsyncCallback<List<TransitLPPot<T>>> callback);

	void getAllLinks(String id, AsyncCallback<List<String>> callback);

	void addLink(String id, String link, AsyncCallback<Boolean> callback);

	void removeLink(String id, String link, AsyncCallback<Boolean> callback);

	void addStoredEntitiy(String id, T entity,
			AsyncCallback<T> callback);

}
