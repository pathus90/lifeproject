package com.unilorraine.projetdevie.client.shared.jdoentities.accountentities;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;

/**
 * JDO db class represent an Actor in a user projects can be a teacher, a parent, a representative, medic ect defined by the user group
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPActor extends AbstractLPAccounts<TransitLPActor>{

	/**
	 * Id of the groups the Actor is in
	 */
	@Persistent
	private ArrayList<String> groups;
	
	/**
	 * Id of the users linked to this actor
	 */
	@Persistent
	private ArrayList<String> users;
	
	/**
	 * Basic constructor for an Actor
	 * @param lastname
	 * @param firstname
	 * @param email
	 * @param description
	 * @param pictureLink
	 * @param instution
	 */
	public LPActor(String lastname, String firstname, String email,
			String description, String pictureLink) {
		super(lastname, firstname, email, description, pictureLink);
		groups = new ArrayList<String>();
		users = new ArrayList<String>();
	}
	
	public LPActor(){
		super();
		groups = new ArrayList<String>();
		users = new ArrayList<String>();
	}

	public boolean addGroup(String e) {
		return groups.add(e);
	}

	public String getGroup(int index) {
		return groups.get(index);
	}

	public int indexOfGroup(String o) {
		return groups.indexOf(o);
	}

	public ListIterator<String> groupsIterator() {
		return groups.listIterator();
	}

	public String removeGroup(int index) {
		return groups.remove(index);
	}

	public boolean removeGroup(String o) {
		return groups.remove(o);
	}

	public int sizeOfGroups() {
		return groups.size();
	}
	
	public boolean addUser(String e) {
		return users.add(e);
	}

	public String getUser(int index) {
		return users.get(index);
	}

	public int indexOfUser(String o) {
		return users.indexOf(o);
	}

	public ListIterator<String> userIterator() {
		return users.listIterator();
	}

	public String removeUser(int index) {
		return users.remove(index);
	}

	public int UserSize() {
		return users.size();
	}

	@Override
	public boolean updateFromTransit(TransitLPActor transitEntity) {
		return helperUpdateFromTransit(transitEntity);
	}

	@Override
	public TransitLPActor createTransit() {
		TransitLPActor transit = new TransitLPActor();
		helperFillTransit(transit);
		for(String key : groups){
			transit.getGroups().add(key);
		}
		for(String key : users){
			transit.getUsers().add(key);
		}
		return transit;
	}
	
	

}
