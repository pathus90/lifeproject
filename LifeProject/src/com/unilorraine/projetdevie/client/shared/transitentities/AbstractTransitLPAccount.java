package com.unilorraine.projetdevie.client.shared.transitentities;

import java.util.ArrayList;


public class AbstractTransitLPAccount implements ITransitEntity {

	/**
	 * The id for this entity
	 */
	 private String id;
	 
	 /**
	  * Last name of the account owner
	  */
	 private String lastname;
	 
	 /**
	  * First name of the account owner
	  */
	 private String firstname;
	 
	 /**
	  * Email of the account owner
	  */
	 private String email;
	 
	 /**
	  * Description of the account owner
	  */
	 private String description;
	
	 /**
	  * Picture of the account owner
	  */
	 private String pictureLink;
	 
	 /**
	  * Institution ID of the account owner
	  */
	 private ArrayList<String> institutions;
	 
	 
	public AbstractTransitLPAccount(String id, String lastname, String firstname,
			String email, String description, String pictureLink) {
		super();
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.description = description;
		this.pictureLink = pictureLink;
		this.institutions = new ArrayList<String>();
	}
	

	public AbstractTransitLPAccount() {
		super();
		this.id = "";
		this.lastname = "";
		this.firstname = "";
		this.email = "";
		this.description = "";
		this.pictureLink = "";
		this.institutions = new ArrayList<String>();
	}


	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public ArrayList<String> getInstitutions() {
		return institutions;
	}

	public void setInstitutions(ArrayList<String> institutions) {
		this.institutions = institutions;
	}

}
