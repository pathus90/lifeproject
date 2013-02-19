package com.unilorraine.projetdevie.client.shared.transitentities;

/**
 * This abstract class is the mother of the project entity wrapper. This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 *
 */
public class AbstractTransitLPEntity implements ITransitEntity {

	/**
	 * The encoded id for this entity
	 */
	 private String id;
	 
	/**
	 * Name of this entity
	 */
	private String name;
	
	/**
	 * Description of this entity
	 */
	private String description;
	
	/**
	 * Css relative link to the image
	 * TODO look how to work the images
	 */
	private String imageLink;
	
	/**
	 * Status of the entity, true if accomplished false otherwise
	 */
	private boolean status;
	
	/**
	 * Progess of this entity in %
	 */
	private int progress;
	
	/**
	 * The Id of the schema from witch this task originated
	 */
	private String schemaID;

	
	/**
	 * Basic constructor for an AbsctractTransitLPEntity.
	 * @param name
	 * @param description
	 * @param imageLink
	 * @param status
	 */
	public AbstractTransitLPEntity(String id, String name, String description, String imageLink,
			boolean status, int progress, String shemaId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageLink = imageLink;
		this.status = status;
		this.progress = progress;
		this.schemaID = schemaID;
	}

	/**
	 * Empty constructor for helper methods
	 */
	public AbstractTransitLPEntity() {
		super();
		
		this.name = "";
		this.description = "";
		this.imageLink = "";
		this.status = false;
		this.progress = 0;
		this.schemaID = "";
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
	
	@Override
	public void setId(String id){
		this.id = id;
	}
	
	@Override
	public String getId(){
		return id;
	}


	public String getSchemaID() {
		return schemaID;
	}


	public void setSchemaID(String schemaID) {
		this.schemaID = schemaID;
	}

}
