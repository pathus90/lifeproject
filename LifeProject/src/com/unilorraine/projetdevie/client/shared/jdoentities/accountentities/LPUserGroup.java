package com.unilorraine.projetdevie.client.shared.jdoentities.accountentities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPGroupUser;

/**
 * JDO db Class to define the User group for the application
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPUserGroup extends AbstractLPEntity<TransitLPGroupUser> {

	 /**
	  * Name for this user group
	  */
	 @Persistent
	private String name;
	 
	 /**
	  * Description for this user group
	  */
	 @Persistent
	 private String description;

	 /**
	  * Basic constructor for a user group
	  * @param name
	  * @param description
	  */
	public LPUserGroup(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public LPUserGroup(){
		this("","");
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
	
	@Override
	public boolean updateFromTransit(TransitLPGroupUser transitEntity) {
		setName(transitEntity.getName());
		setDescription(transitEntity.getDescription());
		return true;
	}

	@Override
	public TransitLPGroupUser createTransit() {
		return new TransitLPGroupUser(getId(), getName(), getDescription());
	}
	 
	 
	
}
