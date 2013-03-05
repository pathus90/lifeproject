package com.unilorraine.projetdevie.client.shared.transitentities;

import java.util.ArrayList;
import java.util.List;
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
	  * The category this unit is linked to
	  */
	 private String category;
	 
	/**
	 * The activity unit
	 */
	@Persistent
	private List<String> activityUnit;
	
	public TransitLPActivityUnit(String id,
			List<String> choices, String category) {
		super();
		this.id = id;
		if(choices != null)
			this.activityUnit = choices;
		else
			this.activityUnit = new ArrayList<String>();
		this.category = category;
	}
	
	public TransitLPActivityUnit() {
		this("", null, "");
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getActivityUnit() {
		return activityUnit;
	}

	public void setActivityUnit(ArrayList<String> activityUnit) {
		this.activityUnit = activityUnit;
	}
	
	

}
