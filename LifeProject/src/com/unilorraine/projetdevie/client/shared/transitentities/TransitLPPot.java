package com.unilorraine.projetdevie.client.shared.transitentities;

import java.util.List;

/**
 * The transit object for a Pot. A Pot is simply tying DB entities together. 
 * This is done by putting the objects in the same Pot, meaning giving them the same ancestor. 
 * A pot can also be linked with other entities which are not in the pot, effectively creating a loose many-to-many connection between the entities.
 * @author Christophe
 *
 */
public class TransitLPPot implements ITransitEntity {

	/**
	 * The id linkeing the db entity and the transit object
	 */
	private String id;
	
	/**
	 * The name of the Transitpot
	 */
	private String name;
	
	/**
	 * The description of the Transitpot
	 */
	private String description;
	
	/**
	 * Creates a transit pot object. The Pot object links other entities together by being their root.
	 * @param id the id of the pot
	 * @param name the name of this pot
	 * @param description the description of the pot
	 */
	public TransitLPPot(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public TransitLPPot() {
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
