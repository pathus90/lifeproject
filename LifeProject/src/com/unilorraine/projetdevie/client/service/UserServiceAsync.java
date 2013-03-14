package com.unilorraine.projetdevie.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.service.helperinterfaces.ICrudServiceAsync;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;

public interface UserServiceAsync extends ICrudServiceAsync<TransitLPUser>{
	
	void getUserForEmail(String email, AsyncCallback<TransitLPUser> callback);
	
	void createBuildingProjectFromTransit(String id, TransitLPProject buildingProject, 
			AsyncCallback<TransitLPProject> callback);
	
}
