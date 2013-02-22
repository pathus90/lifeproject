package com.unilorraine.projetdevie.client.shared.jdoentities.projectentites;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractInstanciatorLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;

/**
 * JDO db class. This class holds the activities witch needs to be chosen from for ONE SLOT in the project. It is only suppose to be composed with stereotype activities
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPActivityUnit extends AbstractInstanciatorLPEntity<TransitLPActivityUnit>{

	/**
	 * The maximum of choices for one unit
	 */
	public final static int MAX_ITEM = 5;

	/**
	 * The activity unit
	 */
	@Persistent
	private ArrayList<String> activities;
	
	/**
	 * The category the activity is in
	 */
	@Persistent
	private String category;

	
	/**
	 * Basic constructor for a Unit
	 */
	public LPActivityUnit() {
		super();
		init(null, "");
		
	}
	
	/**
	 *  Copy constructor. The progress and dates are not copied!
	 * @param unit
	 */
	public LPActivityUnit(LPActivityUnit unit){
		super(unit);
		init(unit.activities, unit.getCategory());
		
	}

	/**
	 * Initialisation method
	 * @param activityUnit if is null an empty array will be initialised
	 */
	private void init(ArrayList<String> activityUnit, String category){
		activities = new ArrayList<String>();
		if(activityUnit != null){
			for(String key : activityUnit){
				addActivity(key);
			}
		}
		this.category = category;
	}

	public boolean addActivity(String e) {
		if(activities.size() >= MAX_ITEM)
			return false;
		return activities.add(e);
	}

	public String getActivity(int index) {
		return activities.get(index);
	}

	public int indexOfActivity(String o) {
		return activities.indexOf(o);
	}

	public String removeActivity(int index) {
		return activities.remove(index);
	}

	public boolean removeActivity(String o) {
		return activities.remove(o);
	}

	public ListIterator<String> activityIterator() {
		return activities.listIterator();
	}

	public int size() {
		return activities.size();
	}

	public ArrayList<String> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<String> activities) {
		this.activities = activities;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	//TODO Changing the category of an ActivityUnit like that is quite dangerous for consistency, to be though over...
	@Override
	public boolean updateFromTransit(TransitLPActivityUnit transitEntity) {
		setCategory(transitEntity.getCategory());
		return true;
	}

	@Override
	public TransitLPActivityUnit createTransit() {
		
		TransitLPActivityUnit transit = new TransitLPActivityUnit(getId(), null, getCategory());
		ListIterator<String> it = activityIterator();
		while(it.hasNext()){
			transit.addActivity(it.next());
		}
		return transit;
	}

	@Override
	public LPActivityUnit createInstance() {
		LPActivityUnit unit = new LPActivityUnit(this);
		return unit;
	}
		

}
