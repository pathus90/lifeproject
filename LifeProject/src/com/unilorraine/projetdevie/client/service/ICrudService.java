package com.unilorraine.projetdevie.client.service;

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
	 * Delete a db object represented by the transit, is the same as just sending the Key
	 * @param transitEntity the transit entity to be deleted
	 * @return the deleted entity, null if errors
	 */
	public T deleteEntity(T transitEntity);
	
	/**
	 * Delete a db object with that key
	 * @param id the transit entity to be deleted
	 * @return the deleted entity, null if errors
	 */
	public T deleteEntity(String id);
	
	/**
	 * Read the entity represented by the Key id
	 * @param id the Key for this object
	 * @return the transit entity linked with this key
	 */
	public T readEntity(String id);
	
}
