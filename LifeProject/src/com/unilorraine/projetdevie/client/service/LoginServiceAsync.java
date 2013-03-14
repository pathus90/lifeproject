package com.unilorraine.projetdevie.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.shared.transitentities.LoginInfo;

public interface LoginServiceAsync {

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

}
