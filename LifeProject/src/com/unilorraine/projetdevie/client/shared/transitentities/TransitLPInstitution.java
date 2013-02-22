package com.unilorraine.projetdevie.client.shared.transitentities;


/**
 * This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 */
public class TransitLPInstitution implements ITransitEntity {

	/**
	 * The id for this entity
	 */
	 private String id;
	 
	 /**
	  * The name of this institution
	  */
	 private String name;
	 
	 /**
	  * The adresse of the institution
	  */
	 private String adresse;
	 
	 /**
	  * The contact email
	  */
	 private String email;
	 
	/**
	  * Tel for this Institution
	  */
	 private String telNumber;
	
	 
	
	public TransitLPInstitution(String id, String name, String adresse,
			String email, String telNumber) {
		super();
		this.id = id;
		this.name = name;
		this.adresse = adresse;
		this.email = email;
		this.telNumber = telNumber;
	}
	
	public TransitLPInstitution() {
		this("", "", "", "", "");
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

}
