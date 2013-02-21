package com.unilorraine.projetdevie.client.shared.jdoentities.projectentites;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractInstanciatorLPEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;

/**
 * JDO db class. This class holds the activities witch needs to be choosen from for ONE SLOT in the project. It is only suppose to be composed with stereotype activities
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
	private ArrayList<String> activityUnit;
	
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
		init(null);
		
	}
	
	/**
	 *  Copy constructor. The progress and dates are not copied!
	 * @param unit
	 */
	public LPActivityUnit(LPActivityUnit unit){
		super(unit);
		init(unit.activityUnit);
		this.category = unit.getCategory();
		
	}

	/**
	 * Initialisation method
	 * @param activityUnit if is null an empty array will be initialised
	 */
	private void init(ArrayList<String> activityUnit){
		activityUnit = new ArrayList<String>();
		if(activityUnit != null){
			for(String key : activityUnit){
				addActivity(key);
			}
		}
		this.category = category;
	}

	public boolean addActivity(String e) {
		if(activityUnit.size() >= MAX_ITEM)
			return false;
		return activityUnit.add(e);
	}

	public String getActivity(int index) {
		return activityUnit.get(index);
	}

	public int indexOfActivity(String o) {
		return activityUnit.indexOf(o);
	}

	public String removeActivity(int index) {
		return activityUnit.remove(index);
	}

	public boolean removeActivity(String o) {
		return activityUnit.remove(o);
	}

	public ListIterator<String> activityIterator() {
		return activityUnit.listIterator();
	}

	public int size() {
		return activityUnit.size();
	}

	public ArrayList<String> getActivityUnit() {
		return activityUnit;
	}

	public void setActivityUnit(ArrayList<String> activityUnit) {
		this.activityUnit = activityUnit;
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
		unit.setSchemaID(this.getId());
		return unit;
	}
		

}
