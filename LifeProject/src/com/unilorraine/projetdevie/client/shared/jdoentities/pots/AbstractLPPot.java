package com.unilorraine.projetdevie.client.shared.jdoentities.pots;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitHelper;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
/**
 * JDO abstract class for a {@link IPot} it can hold {@link T} entities. 
 * It handles the basic functions for the stored entities and the links.
 * The link entities are not consistency assured by this class, they are just references and deleting them from this entity will not delete them.
 * The sotored entities on the other hand need to be brand new before being added here or it will result in an error. If they removed from the pot, 
 * they will be destroyed on the next persistence action (for ex. makePersistent).
 * The getter and setter you need to overload are required because you can not make generic types persistent nor use polymorphism. 	 
 * Think about the fact that you are also responsible for avoiding any NullPointer exception by initializing the List.
 * !! You need to make the list persistent yourself if you want it saved in the db !!
 * 
 * @author Christophe
 * @param <T> the type of {@link ITransitEntity} the stored entity is producing
 * @param <S> the type of {@link AbstractLPEntity} this pot can store
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractLPPot<T extends ITransitEntity, S extends AbstractLPEntity<T>> extends AbstractLPEntity<TransitLPPot<T>> implements IPot<S>{

	/**
	 * The name of the pot
	 */
	@Persistent
	private String name;
	
	/**
	 * The description of the pot
	 */
	@Persistent
	private String description;
	
	/**
	 * This method needs to return the list of stored entities.
	 * !! You need to make it persistent your self if you want it saved in the db !!
	 * This method was required because you can not make generic types persistent nor use polymorphism.
	 * The initialization is done for you by the super constructor.
	 * @return the list of stored entities.
	 */
	protected abstract List<S> getStoredEntities();
	
	/**
	 * This method needs to set the list of stored entities.
	 * !! You need to make it persistent your self if you want it saved in the db !!
	 * This method was required because you can not make generic types persistent nor use polymorphism.
	 * The initialization is done for you by the super constructor.
	 * @param storedEntities the list of entities
	 */
	protected abstract void setStoredEntities( List<S> storedEntities);
	
	/**
	 * The list of entities linked with the pot
	 */
	@Persistent
	private List<String>  linkedEntities;
	
	/**
	 * Default constructor for a pot, the name and description will be set to an empty string and the lists will be initialised.
	 */
	public AbstractLPPot(){
		this("", "", null, null);
	}

	/**
	 * Create a pot based on a name and a description.
	 * @param name the name of the pot
	 * @param description the description of the pot
	 */
	public AbstractLPPot(String name, String description) {
		this(name, description, null, null);
	}

	/**
	 * Create a pot based on a name, a description. 
	 * If the stored list or/and the link list is null it will be instantiated, so it it can be used through functions.
	 * @param name the name of the pot
	 * @param description the description of the pot
	 * @param storedEntities the list of entity stored in the pot
	 * @param linkedEntities the list of entity this pot is linked with
	 */
	public AbstractLPPot( String name, String description, List<S> storedEntites, List<String> linkedEntities) {
		super();
		
		setName(name);
		setDescription(description);
		
		if(storedEntites != null)
			setStoredEntities(storedEntites);
		else
			setStoredEntities(new ArrayList<S>());
		
		if(linkedEntities != null)
			this.linkedEntities = linkedEntities;
		else
			this.linkedEntities = new ArrayList<String>();
	}



	@Override
	public String getName() {
		return name;
	}



	@Override
	public void setName(String name) {
		this.name = name;
		
	}



	@Override
	public String getDescription() {
		return description;
	}



	@Override
	public void setDescription(String description) {
		this.description = description;
	}



	@Override
	public boolean addEntity(S entity) {
		return getStoredEntities().add(entity);
	}
	
	@Override
	public boolean removeEntitiy(String idEntity){
		for(S entity : getStoredEntities()){
			if(entity.getId().equals(idEntity)){
				return getStoredEntities().remove(entity);
			}
		}
		return false;
	}

	@Override
	public S getStoredEntity(String idEntity) {
		for(S entity : getStoredEntities()){
			if(entity.getId().equals(idEntity)){
				return entity;
			}
		}
		return null;
	}

	@Override
	public List<S> getAllStoredEntities() {
		return getStoredEntities();
	}

	@Override
	public boolean addLink(String linkId) {
		if(isLinked(linkId))
			return true;
		else{
			return linkedEntities.add(linkId);
		}
	}

	@Override
	public boolean removeLink(String linkId) {
		return linkedEntities.remove(linkId);
	}

	@Override
	public boolean isLinked(String linkId) {
		return linkedEntities.contains(linkId);
	}

	@Override
	public List<String> getAllLink() {
		return linkedEntities;
	}

	@Override
	public boolean updateFromTransit(TransitLPPot<T> transitEntity) {
		setName(transitEntity.getName());
		setDescription(transitEntity.getDescription());
		return true;
	}

	@Override
	public TransitLPPot<T> createTransit() {
		TransitHelper<T, S> helper = new TransitHelper<T, S>();
		return new TransitLPPot<T>(getId(), getName(), getDescription(), helper.createTransitEntities(getAllStoredEntities()));
	}

}
