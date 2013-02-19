package com.unilorraine.projetdevie.client.shared.jdoentities.accountentities;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;

/**
 * JDO db class to represent the institution in witch either the Actors or the user are linked to
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPInstitution extends AbstractLPEntity<TransitLPInstitution>{
	 
	 /**
	  * The name of this institution
	  */
	 @Persistent
	 private String name;
	 
	 /**
	  * The adresse of the institution
	  */
	 @Persistent
	 private String adresse;
	 
	 /**
	  * The contact email
	  */
	 @Persistent
	 private String email;
	 
	/**
	  * Tel for this Institution
	  */
	 @Persistent
	 private String telNumber;

	 /**
	  * Basic constructor for an institution
	  * @param name
	  * @param adresse
	  * @param email
	  * @param telNumber
	  */
	public LPInstitution(String name, String adresse, String email, String telNumber) {
		super();
		this.name = name;
		this.adresse = adresse;
		this.email = email;
		this.telNumber = telNumber;
	}
	
	public LPInstitution(){
		this("", "", "", "");
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
	 
	@Override
	public boolean updateFromTransit(TransitLPInstitution transitEntity) {
		setName(transitEntity.getName());
		setEmail(transitEntity.getEmail());
		setTelNumber(transitEntity.getTelNumber());
		setAdresse(transitEntity.getAdresse());
		return true;
	}

	@Override
	public TransitLPInstitution createTransit() {
		return new TransitLPInstitution(getId(), getName(), getAdresse(), getEmail(), getTelNumber());
	}

}
