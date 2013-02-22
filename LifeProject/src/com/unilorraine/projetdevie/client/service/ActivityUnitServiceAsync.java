package com.unilorraine.projetdevie.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;

public interface ActivityUnitServiceAsync extends ICrudServiceAsync<TransitLPActivityUnit> {

	void addActivity(String activityUnitId, String activitySchemaId,
			AsyncCallback<TransitLPActivityUnit> callback);

	void removeActivity(String activityUnitId, String activitySchemaId,
			AsyncCallback<TransitLPActivityUnit> callback);

}
