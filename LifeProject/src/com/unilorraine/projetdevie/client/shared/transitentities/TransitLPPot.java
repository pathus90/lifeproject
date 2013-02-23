package com.unilorraine.projetdevie.client.shared.transitentities;

import java.util.ArrayList;
import java.util.List;

/**
 * The transit object for a Pot. A Pot is simply tying DB entities together. 
 * This is done by putting the objects in the same Pot, meaning giving them the same ancestor. 
 * A pot can also be linked with other entities which are not in the pot, effectively creating a loose many-to-many connection between the entities.
 * @author Christophe
 * 
 *@param T the kind of transitable entity transported in this pot
 *
 */
public class TransitLPPot<T extends ITransitEntity> implements ITransitEntity {

	/**
	 * The id linking the db entity and the transit object
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
	 * The list of the stored entities in this pot in transit form.
	 */
	private List<T> storedEntities;
	
	/**
	 * Creates a transit pot object. The Pot object links other entities together by being their root.
	 * @param id the id of the pot
	 * @param name the name of this pot
	 * @param description the description of the pot
	 * @param storedEntities the stored entities in transit form. This will not be updated in the db on the server side if you try an update from transit.
	 */
	public TransitLPPot(String id, String name, String description, List<T> storedEntities) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		if(storedEntities != null)
			this.storedEntities = storedEntities;
		else
			this.storedEntities = new ArrayList<T>();
	}

	public TransitLPPot() {
		this("", "", "", null);
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

	public List<T> getStoredEntities() {
		return storedEntities;
	}

	public void setStoredEntities(List<T> storedEntities) {
		this.storedEntities = storedEntities;
	}
	
	

}
