package com.unilorraine.projetdevie.client.ui.binder;

import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * Interface representing an graphical editor for transit entities
 * @author Christophe
 *
 * @param <T>
 */
public interface EntityEditor<T> {
	
	/**
	 * Push an entity in the editor
	 * @param fillingEntity the entity to be pushed in
	 */
	public void push(T fillingEntity);
	
	/**
	 * Pull an entity out of the editor
	 * @return the entity to be pulled out
	 */
	public T pull();
	
	

}
