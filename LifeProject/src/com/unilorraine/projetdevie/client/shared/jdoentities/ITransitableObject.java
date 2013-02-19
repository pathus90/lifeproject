package com.unilorraine.projetdevie.client.shared.jdoentities;

import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * Interface to define DB-Objects that are able to be transited over RPC. To do so, they need a ITransitEntity linked to them 
 * to act as a representative on the cleint side because for example JDO objects can not be serialized easily.
 * @author Christophe
 *
 * @param <T>
 */
public interface ITransitableObject<T extends ITransitEntity> {

	/**
	 * Update the transitable object with a TransitEntity. This method needs to update all the fields from transitEntity into the DB Object!
	 * @param transitEntity the transit entity that will update the DB object
	 * @return true if update successful, false if not
	 */
	public boolean updateFromTransit(T transitEntity);
	
	/**
	 * This method needs to be able to create a ITransitEntity from a transitable DB Object        
	 * @return the ITransitEntity object
	 */
	public T createTransit();
	
}
