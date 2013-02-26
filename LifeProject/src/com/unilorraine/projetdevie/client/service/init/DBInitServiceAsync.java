package com.unilorraine.projetdevie.client.service.init;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;

public interface DBInitServiceAsync {

	void initMethod(AsyncCallback<List<ITransitEntity>> callback);

}
