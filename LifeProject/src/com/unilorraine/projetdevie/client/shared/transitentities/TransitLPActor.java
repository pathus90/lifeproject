package com.unilorraine.projetdevie.client.shared.transitentities;

import java.util.ArrayList;

/**
 * This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 *
 */
public class TransitLPActor extends AbstractTransitLPAccount {

	/**
	 * The groups this actor is linked to. This parameter wont change the DB objects in case of an update.
	 */
	private ArrayList<String> groups;
	
	/**
	 * The users this actor is linked to. This parameter wont change the DB objects in case of an update.
	 */
	private ArrayList<String> users;
	
	public TransitLPActor(String id, String lastname, String firstname,
			String email, String description, String pictureLink) {
		super(id, lastname, firstname, email, description, pictureLink);
		groups = new ArrayList<String>();
		users = new ArrayList<String>();
	}

	public TransitLPActor() {
		super();
		groups = new ArrayList<String>();
		users = new ArrayList<String>();
	}

	public ArrayList<String> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<String> groups) {
		this.groups = groups;
	}

	public ArrayList<String> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}

}
