package com.unilorraine.projetdevie.client.shared.jdoentities.projectentites;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractInstanciatorLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.AbstractTransitLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * JDO db abstract class for the commons object of a life project. Like Task, Activity or Project
 * 
 * @author Christophe
 *
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractLPProjectEntity<T extends ITransitEntity> extends AbstractInstanciatorLPEntity<T>{

	 
	/**
	 * Name of this entity
	 */
	@Persistent
	private String name;
	
	/**
	 * Description of this entity
	 */
	@Persistent
	private String description;
	
	/**
	 * Css relative link to the image
	 * TODO look how to work the images
	 */
	@Persistent
	private String imageLink;
	
	/**
	 * Status of the entity, true if accomplished false otherwise
	 */
	@Persistent
	private boolean status;
	
	/**
	 * Progess of this entity in %
	 */
	@Persistent
	private int progress;
	
	/**
	 * Date of creation for this entity
	 */
	@Persistent
	private Date dateOfCreation;
	
	/**
	 * Date of last update for this entity
	 */
	@Persistent
	private Date lastUpdate;

	
	/**
	 * Basic constructor for an AbsctractLPEntity. The dates are set up automaticly and the progress is set to 0%.
	 * @param name
	 * @param description
	 * @param imageLink
	 * @param status
	 */
	public AbstractLPProjectEntity(String name, String description, String imageLink,
			boolean status) {
		super();
		init(name, description, imageLink, status);
		
	}
	
	/**
	 * Copy Constructor. The progress and dates are not copied and the schema ID will be set to the id of the instantiator
	 * @param abstractLPEntity to be copied
	 */
	public AbstractLPProjectEntity(AbstractLPProjectEntity abstractLPEntity){
		//super(abstractLPEntity);
		init(abstractLPEntity.getName(), abstractLPEntity.getDescription(), abstractLPEntity.getImageLink(), abstractLPEntity.isStatus());
		setSchemaID(abstractLPEntity.getId());
	}
	
	public AbstractLPProjectEntity(){
		this("", "", "", false);
	}
	
	/**
	 * Initialisation method, needed because of multiple constructors
	 * @param name
	 * @param description
	 * @param imageLink
	 * @param status
	 */
	private void init(String name, String description, String imageLink,
			boolean status){
		this.name = name;
		this.description = description;
		this.imageLink = imageLink;
		this.status = status;
		
		Calendar cal = Calendar.getInstance();
		
		this.dateOfCreation = cal.getTime();
		this.lastUpdate = this.dateOfCreation;
		this.progress = 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progess) {
		this.progress = progess;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate() {
		Calendar cal = Calendar.getInstance();
		this.lastUpdate = cal.getTime();
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	
	
	
	/**
	 * Helper class for the implementation of the transit update method in the children classes
	 * @param transitEntity the transit entity will update from
	 * @return true if all was good
	 */
	
	protected boolean helperUpdateFromTransit(AbstractTransitLPEntity transitEntity) {
		setName(transitEntity.getName());
		setDescription(transitEntity.getDescription());
		setImageLink(transitEntity.getImageLink());
		setStatus(transitEntity.isStatus());
		setProgress(transitEntity.getProgress());
		setLastUpdate();
		return true;
	}
	
	/**
	 * Helper class for the implementation of the transit creator in children classes
	 * @param entity to be created
	 */
	protected void helperFillTransit(AbstractTransitLPEntity entity){
		entity.setName(getName());
		entity.setDescription(getDescription());
		entity.setImageLink(getImageLink());
		entity.setStatus(isStatus());
		entity.setProgress(getProgress());
		entity.setSchemaID(getSchemaID());
		entity.setId(getId());
	}
	
	
}
