package com.unilorraine.projetdevie.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBTestMaterialServiceAsync {

	void populateDB(AsyncCallback<Void> callback);

}
