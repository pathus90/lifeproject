package com.unilorraine.projetdevie.client.shared.transitentities;

/**
 * This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 *
 */
public class TransitLPActivity extends AbstractTransitLPEntity{

	/**
	 * The category the activity is in
	 */
	private String category;
	
	public TransitLPActivity(String id, String name, String description, String imageLink,
			boolean status, int progress, String category, String shemaId) {
		super(id, name, description, imageLink, status, progress, shemaId);
		
		this.category = category;
	}
	
	public TransitLPActivity() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

}
