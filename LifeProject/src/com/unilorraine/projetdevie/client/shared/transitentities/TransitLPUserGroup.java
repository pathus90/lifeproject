package com.unilorraine.projetdevie.client.shared.transitentities;


/**
 * This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 *
 */
public class TransitLPUserGroup implements ITransitEntity {

	/**
	 * The id for this entity
	 */
	 private String id;
	 
	 /**
	  * Name for this user group
	  */
	private String name;
	 
	 /**
	  * Description for this user group
	  */
	 private String description;
	 
	 
	public TransitLPUserGroup(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public TransitLPUserGroup() {
		this("", "", "");
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

}
