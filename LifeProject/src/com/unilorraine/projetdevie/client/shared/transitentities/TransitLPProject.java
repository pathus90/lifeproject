package com.unilorraine.projetdevie.client.shared.transitentities;

/**
 * This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 *
 */
public class TransitLPProject extends AbstractTransitLPEntity {
	
	public TransitLPProject(String id, String name, String description, String imageLink,
			boolean status, int progress, String shemaId) {
		super(id, name, description, imageLink, status, progress, shemaId);
	}

	public TransitLPProject() {
		super();
	}

}
