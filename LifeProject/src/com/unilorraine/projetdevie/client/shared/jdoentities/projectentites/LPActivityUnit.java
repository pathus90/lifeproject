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

	@Override
	public boolean updateFromTransit(TransitLPActivityUnit transitEntity) {
		return true;
	}

	@Override
	public TransitLPActivityUnit createTransit() {
		
		TransitLPActivityUnit transit = new TransitLPActivityUnit(getId(), null);
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
