package com.unilorraine.projetdevie.client.shared.jdoentities.pots;

import java.util.List;

import com.unilorraine.projetdevie.client.shared.jdoentities.IDBEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
/**
 * A pot is a BD entity which is used as Root element for other elements. This Interface makes no regard of what is stored.
 * A pot just needs its stored entities to be {@link IDBEntity} compliant.
 * A pot also links other entities to the stored entities by holding keys to the links.
 * The pot does not ensure the consistency of its links.
 * This will allow to create a specific usage context for a pot.
 * 
 * @param <T> the specific entity the implementing class stores 
 * @author Christophe
 *
 */
public interface IPot<T extends IDBEntity> extends IDBEntity {

	/**
	 * Set the name of this pot
	 * @return the name of the pot
	 */
	public String getName();
	
	/**
	 * Sets the name of this pot
	 * @param name the new name of the pot
	 */
	public void setName(String name);
	
	/**
	 * Gets the description of the pot
	 * @return the description of the pot
	 */
	public String getDescription();
	
	/**
	 * Sets the description of the pot
	 * @param description the new description of the pot
	 */
	public void setDescription(String description);
	
	/**
	 * Add an entity to this pot. The entity will be linked to this pot after it has been updated
	 * This entity should not already persistent, this would produce an error.
	 * @param entity to be added
	 * @return true if it could be added
	 */
	public boolean addEntity(T entity);
	
	/**
	 * Remove an entity from the pot.
	 * @param idEntity the id of the entity to be removed
	 * @return true if has been removed, false if an error occurred (for example id the entity does not exist in the pot)
	 */
	public boolean removeEntitiy(String idEntity);
	
	/**
	 * Get the entity by the passed id
	 * @param idEntity
	 * @return the entity found, null if an error occurs
	 */
	public T getStoredEntity(String idEntity);
	
	/**
	 * Get all the stored entities of this pot
	 * @return a list of entites
	 */
	public List<T> getAllStoredEntities();
	
	/**
	 * Links this pot to another entity
	 * @param linkId the id of the entity to be linked with
	 * @return true if the pot could be linked, false if an error occurred
	 */
	public boolean addLink(String linkId);
	
	/**
	 * Remove a linked entity form the pot
	 * @param linkId the id of the entity to be removed as link
	 * @return true if the removal went good, false if not
	 */
	public boolean removeLink(String linkId);
	
	/**
	 * Checks if the given id of an entity is linked with the pot
	 * @param linkId the id of the entity to be checked
	 * @return true if the id is linked, false if not
	 */
	public boolean isLinked(String linkId);
	
	/**
	 * Get a list of all the id of the entities linked to this pot
	 * @return id list of the linked entities
	 */
	public List<String> getAllLink();
	
}
