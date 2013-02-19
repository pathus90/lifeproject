package com.unilorraine.projetdevie.client.shared.jdoentities.projectentites;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;

/**
 * JDO db class to define a Category in witch is an Activity
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPCategory extends AbstractLPEntity<TransitLPCategory> {

	 
	 /**
	  * Name for that category
	  */
	 @Persistent
	 private String name;
	 
	 /**
	  * Description to that category
	  */
	 @Persistent
	 private String description;

	 /**
	  * Basic constructor for a category
	  * @param name
	  * @param description
	  * @param linktoImage
	  */
	 public LPCategory(String name, String description, String linktoImage) {
			super();
			this.name = name;
			this.description = description;
			this.linktoImage = linktoImage;
		}
	 
	 public LPCategory() {
			this("", "", "");
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

	public String getLinktoImage() {
		return linktoImage;
	}

	public void setLinktoImage(String linktoImage) {
		this.linktoImage = linktoImage;
	}

	/**
	  * Link to the css image
	  * TODO Look up how to work with images
	  */
	 @Persistent
	 private String linktoImage;

	@Override
	public boolean updateFromTransit(TransitLPCategory transitEntity) {
		setName(transitEntity.getName());
		setDescription(transitEntity.getDescription());
		return true;
	}

	@Override
	public TransitLPCategory createTransit() {
		return new TransitLPCategory(getId(), getName(), getDescription());
	}
}
