package com.unilorraine.projetdevie.client.service.init;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;

public interface DBInitServiceAsync {

	void getInstitution(AsyncCallback<TransitLPInstitution> callback);

	void getUser(AsyncCallback<TransitLPUser> callback);

	void getActor(AsyncCallback<TransitLPActor> callback);

	void initMethod(AsyncCallback<Void> callback);

}
