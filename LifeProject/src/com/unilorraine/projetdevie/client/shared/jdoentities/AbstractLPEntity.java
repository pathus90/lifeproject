package com.unilorraine.projetdevie.client.shared.jdoentities;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * This is the bastract base class for a POJO JDO based DB object. It needs an ID. 
 * We decided to go with String, because they give a nice level of abstraction from the GAE datastore 
 * but still offers the hierchical propreties of a Key.
 * The DB object are all transitable through a ITransitEntity object and they need to be able to be updated from this transit object as to able to create one.
 * @author Christophe
 *
 * @param <T> the object which will act as the liaison with the client.
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractLPEntity<T extends ITransitEntity> implements ITransitableObject<T>, IDBEntity {

	/**
	 * The id for this entity in an encoded String form
	 */
	 @PrimaryKey  
	 @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)  
	 @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	 private String id;
	
	public AbstractLPEntity() {
	}

	@Override
	public abstract boolean updateFromTransit(T transitEntity);

	@Override
	public abstract T createTransit();
	
	/**
	 * Get the id in an encoded String form
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}
	
	/**
	 *  Set the id in an encoded String form
	 * @param id the encoded id
	 */
	public void setId(String id) {
		this.id = id;
	}

}
