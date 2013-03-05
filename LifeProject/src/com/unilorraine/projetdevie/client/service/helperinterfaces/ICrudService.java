package com.unilorraine.projetdevie.client.service.helperinterfaces;

import java.util.List;

import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

public interface ICrudService<T extends ITransitEntity> {

	/**
	 * Create an db entity from a Transit entity
	 * @param transitEntity the transit to create from
	 * @return the created transit, null if error. The only difference with the parameter should be a Key
	 */
	public T createEntity(T transitEntity);
	
	/**
	 * Create a new db entity
	 * @return the created transit, null if error. The only difference with the parameter should be a Key
	 */
	public T createEntity();
	
	/**
	 * Update a db Entity from a transit
	 * @param transitEntity the transit to update from
	 * @return the update transit, should not be very different from the parameter
	 */
	public T updateEntity(T transitEntity);
	
	/**
	 * Update a db Entities from a transits
	 * @param transitEntities list of transits to update from
	 * @return the list updated transit, should not be very different from the parameter. If an error occurred for one transit it will not be in the returned list 
	 */
	public List<T> updateEntities(List<T> transitEntities);
	
	/**
	 * Delete a db object represented by the transit, is the same as just sending the Key
	 * @param transitEntity the transit entity to be deleted
	 * @return the deleted entity, null if errors
	 */
	public T deleteEntity(T transitEntity);
	
	/**
	 * Delete a db object represented by an in
	 * @param id the id to be deleted
	 * @return  the deleted entity, null if errors
	 */
	public T deleteEntity(String id);
	
	/**
	 * Delete a db objects represented by the transit, is the same as just sendng a list of ids
	 * @param transitEntities the list of transits entities to be deleted
	 * @return the list of the deleted entities, not deleted entities will not be in the returned in the list
	 */
	public List<T> deleteEntities(List<T> transitEntities);
	
	/**
	 * Delete a db object represented with the passed ids
	 * @param entitiesId the list of ids of the entities to be deleted
	 * @return the list of the deleted entities, not deleted entities will not be in the returned in the list
	 */
	public List<T> deleteEntitiesFromId(List<String> entitiesId);
	
	/**
	 * Read the entity represented by the Key id
	 * @param id the Key for this object
	 * @return the transit entity linked with this key
	 */
	public T readEntity(String id);
	
	
	/**
	 * Read the entities represented by the list of ids
	 * @param ids the list of ids to be read
	 * @return the list of transit entities linked with the id list, if an id is missing it will not be in the returned list
	 */
	public List<T> readEntities(List<String> ids);
}
