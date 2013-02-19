package com.unilorraine.projetdevie.client.shared.jdoentities;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * Abstract Class to implement if the db object can instanciate other objects. 
 * Every instanciator could eventually have been instanciated, so it has to have a reference to his father.
 * @author Christophe
 *
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractInstanciatorLPEntity<T extends ITransitEntity> extends AbstractLPEntity<T> implements IInstanciator {

	/**
	 * The Id of the schema from witch this task originated, as an encoded String
	 */
	private String schemaID;
	
	public AbstractInstanciatorLPEntity() {
		this.schemaID = "";
	}
	
	public AbstractInstanciatorLPEntity(AbstractInstanciatorLPEntity<T> entityToCopy) {
		schemaID = entityToCopy.getSchemaID();
	} 

	@Override
	public abstract IInstanciator createInstance();
	
	@Override
	public String getSchemaID() {
		return schemaID;
	}

	@Override
	public void setSchemaID(String schemaID) {
		this.schemaID = schemaID;
	}

}
