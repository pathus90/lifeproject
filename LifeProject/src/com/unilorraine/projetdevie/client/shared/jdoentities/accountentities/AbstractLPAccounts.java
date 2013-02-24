package com.unilorraine.projetdevie.client.shared.jdoentities.accountentities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ListIterator;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.google.appengine.api.datastore.Key;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.AbstractTransitLPAccount;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * Abstract part of an user account.
 * @author Christophe
 *
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class AbstractLPAccounts<T extends ITransitEntity>  extends AbstractLPEntity<T> {

	 /**
	  * Last name of the account owner
	  */
	 @Persistent
	 private String lastname;
	 
	 /**
	  * First name of the account owner
	  */
	 @Persistent
	 private String firstname;
	 
	 /**
	  * Email of the account owner
	  */
	 @Persistent
	 private String email;
	 
	 /**
	  * Description of the account owner
	  */
	 @Persistent
	 private String description;
	
	 /**
	  * Picture of the account owner
	  */
	 @Persistent
	 private String pictureLink;
	 
	 /**
	  * Institution ID of the account owner
	  */
	 @Persistent
	 private ArrayList<String> institutions;

	 
	 /**
	  * Basic constructor for an account
	  * @param lastname
	  * @param firstname
	  * @param email
	  * @param description
	  * @param pictureLink
	  * @param instution
	  */
	public AbstractLPAccounts(String lastname, String firstname, String email,
			String description, String pictureLink) {
		super();
		this.lastname = lastname;
		this.firstname = firstname;
		this.email = email;
		this.description = description;
		this.pictureLink = pictureLink;
		this.institutions = new ArrayList<String>();
	}
	
	public AbstractLPAccounts(){
		this("", "", "", "", "");
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

	public boolean addInstution(String e) {
		return institutions.add(e);
	}

	public boolean addAllInstution(Collection<? extends String> c) {
		return institutions.addAll(c);
	}

	public String getInstution(int index) {
		return institutions.get(index);
	}

	public int indexOfInstution(Key o) {
		return institutions.indexOf(o);
	}

	public ListIterator<String> instutiontIterator() {
		return institutions.listIterator();
	}

	public String removeInstution(int index) {
		return institutions.remove(index);
	}

	public boolean removeInstution(String o) {
		return institutions.remove(o);
	}

	public int sizeOfInstution() {
		return institutions.size();
	}
 
	/**
	 * Helper class for the implementation of the transit update method in the children classes
	 * @param transitEntity the transit entity will update from
	 * @return true if all was good
	 */
	
	protected boolean helperUpdateFromTransit(AbstractTransitLPAccount transitEntity) {
		setFirstname(transitEntity.getFirstname());
		setLastname(transitEntity.getLastname());
		setDescription(transitEntity.getDescription());
		setEmail(transitEntity.getEmail());
		setPictureLink(transitEntity.getPictureLink());
		return true;
	}
	
	/**
	 * Helper class for the implementation of the transit creator in children classes
	 * @param entity to be created
	 */
	protected void helperFillTransit(AbstractTransitLPAccount entity){
		entity.setFirstname(getFirstname());
		entity.setLastname(getLastname());
		entity.setDescription(getDescription());
		entity.setPictureLink(getPictureLink());
		for(String key : institutions){
			entity.getInstitutions().add(key);
		}
	}
	
}
