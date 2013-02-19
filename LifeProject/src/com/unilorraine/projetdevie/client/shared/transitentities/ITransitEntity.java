package com.unilorraine.projetdevie.client.shared.transitentities;

import java.io.Serializable;

/**
 * Interface for a transit object able to be sent over RPC and is used by the client. A transit object has to be serializable.
 * @author Christophe
 *
 */
public interface ITransitEntity extends Serializable {

	public String getId();
	public void setId(String id);
	
}
