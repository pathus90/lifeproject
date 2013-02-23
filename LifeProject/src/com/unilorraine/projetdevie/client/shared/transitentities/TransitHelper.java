package com.unilorraine.projetdevie.client.shared.transitentities;

import java.util.ArrayList;
import java.util.List;

import com.unilorraine.projetdevie.client.shared.jdoentities.ITransitableObject;

/**
 * Helper class for transit operations. It alwas needs the {@link ITransitEntity} and the {@link ITransitableObject} associated to do its job.
 * This class cannot be static because it needs generic types.
 * @author Christophe
 *
 * @param <T> the type of {@link ITransitEntity} to be work with
 * @param <S> the type of {@link ITransitableObject} to be work with
 */
public final class TransitHelper<T extends ITransitEntity, S extends ITransitableObject<T>> {
	
	/**
	 * Creates a list of transit objects out of a {@link ITransitableObject} list.
	 * If an object of the parameter list returns null by calling {@link ITransitableObject#createTransit()} 
	 * it will be ignored and not placed in the outgoinging transit list.
	 * So be aware that the index could not match.
	 * @param list the list to be transited
	 * @return a list of transit objects from the given parameter
	 */
	public List<T> createTransitEntities(List<S> list){
		ArrayList<T> transitList = new ArrayList<T>();
		for(ITransitableObject<T> transitable : list){
			T tmpTransit = transitable.createTransit();
			if(tmpTransit != null){
				transitList.add(tmpTransit);
			}
		}
		return transitList;
	}

}
