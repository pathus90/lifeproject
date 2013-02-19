package com.unilorraine.projetdevie.client.shared.jdoentities;

/**
 * This interface defines an entity capable of copying itself.
 * Every instanciator could eventually have been instanciated, so it has to have a reference to his father.
 * @author Christophe
 */
public interface IInstanciator {
	
	/**
	 * Create an instance
	 * @return the instanciated entity
	 */
	public IInstanciator createInstance();
	
	/**
	 * Get the id of the object from witch this one originated
	 * @return encoded String id of the instanciator object
	 */
	public String getSchemaID();
	

	/**
	 * Set the id of the object from witch this one originated
	 * @param schemaId the encoded String id
	 */
	public void setSchemaID(String schemaId);
}
