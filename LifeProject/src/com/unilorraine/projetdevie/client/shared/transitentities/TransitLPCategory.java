package com.unilorraine.projetdevie.client.shared.transitentities;


/**
 * This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 *
 */
public class TransitLPCategory implements ITransitEntity {

	/**
	 * The id for this entity
	 */ 
	 private String id;
	 
	 /**
	  * Name for that category
	  */
	 private String name;
	 
	 /**
	  * Description to that category
	  */
	 private String description;
	 
	 /**
	  * Link to the image
	  */
	 private String imageLink;
	
	public TransitLPCategory(String id, String name, String description, String imageLink) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageLink = imageLink;
	}
	
	public TransitLPCategory(){
		this("", "", "", "");
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
		
	}
	
	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	

}
