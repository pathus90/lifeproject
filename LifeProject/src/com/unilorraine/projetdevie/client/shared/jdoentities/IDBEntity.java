package com.unilorraine.projetdevie.client.shared.jdoentities;

import javax.jdo.annotations.Persistent;

/**
 * A DB entity must have an id. We decided to use the String format because it has the power of the tree structure but is alos portable.
 * @author Christophe
 *
 */
public interface IDBEntity {
	
	/**
	 * The id must be accessible.
	 * @return the id for this entity
	 */
	public String getId();

}
