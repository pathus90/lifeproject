package com.unilorraine.projetdevie.client.shared.transitentities;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.jdo.annotations.Persistent;

/**
 * This objects are the one transiting through the RPC channels.
 * They are used as link between the server and the client.
 * @author Christophe
 *
 */
public class TransitLPActivityUnit implements ITransitEntity {

	/**
	 * The id for this entity
	 */
	 private String id;
	 

	/**
	 * The activity unit
	 */
	@Persistent
	private ArrayList<String> activityUnit;
	
	public TransitLPActivityUnit(String id,
			ArrayList<String> activityUnit) {
		super();
		this.id = id;
		if(activityUnit != null)
			this.activityUnit = activityUnit;
		else
			this.activityUnit = new ArrayList<String>();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	public void addActivity(int index, String element) {
		activityUnit.add(index, element);
	}

	public boolean addActivity(String e) {
		return activityUnit.add(e);
	}

	public String getActivity(int index) {
		return activityUnit.get(index);
	}

	public String removeActivity(int index) {
		return activityUnit.remove(index);
	}

	public ListIterator<String> activityIterator() {
		return activityUnit.listIterator();
	}

	public int sizeOfUnit() {
		return activityUnit.size();
	}
	
	

}