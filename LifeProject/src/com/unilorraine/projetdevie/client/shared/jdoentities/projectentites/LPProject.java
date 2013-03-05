package com.unilorraine.projetdevie.client.shared.jdoentities.projectentites;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.jdoentities.IInstanciator;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;

/**
 * JDO db class of a life project. Is composed of {@link LPActivity}
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPProject extends AbstractLPProjectEntity<TransitLPProject> implements IInstanciator{

	/**
	 * List of activities in this project
	 */
	@Persistent
	private ArrayList<LPActivity> activities;
	
	/**
	 * List of activity units from witch an activity must be chosen
	 */
	@Persistent
	private ArrayList<LPActivityUnit> choiceUnit;
	
	/**
	 * Basic constructor for a life project. The activity list is set and empty
	 * @param name
	 * @param description
	 * @param imageLink
	 * @param status
	 */
	public LPProject(String name, String description, String imageLink,
			boolean status) {
		super(name, description, imageLink, status);
		
		activities = new ArrayList<LPActivity>();
	}
	
	/**
	 * Copy constructor. The progress and dates are not copied!
	 * @param project
	 */
	public LPProject(LPProject project){
		super(project);
		for(LPActivityUnit unit : project.choiceUnit){
			choiceUnit.add(new LPActivityUnit(unit));
		}
		for(LPActivity activity : project.activities){
			activities.add(new LPActivity(activity));
		}
		
	}
	
	public LPProject() {
		super();
		activities = new ArrayList<LPActivity>();
		choiceUnit = new ArrayList<LPActivityUnit>();
		
	}

	public void resetActivities(){
		activities = new ArrayList<LPActivity>();
	}
	
	public boolean addActivity(LPActivity Activity) {
		return activities.add(Activity);
	}

	public LPActivity getActivity(int index) {
		return activities.get(index);
	}

	public int indexOfActivity(LPActivity Activity) {
		return activities.indexOf(Activity);
	}

	public LPActivity removeActivity(int index) {
		return activities.remove(index);
	}

	public boolean removeActivity(LPActivity Activity) {
		return activities.remove(Activity);
	}

	public int sizeOfActivities() {
		return activities.size();
	}

	public ListIterator<LPActivity> activityIterator() {
		return activities.listIterator();
	}

	public void addUnit(int index, LPActivityUnit element) {
		choiceUnit.add(index, element);
	}

	public boolean addUnit(LPActivityUnit e) {
		return choiceUnit.add(e);
	}

	public LPActivityUnit getUnit(int index) {
		return choiceUnit.get(index);
	}

	public int indexOfUnit(Object o) {
		return choiceUnit.indexOf(o);
	}

	public ListIterator<LPActivityUnit> unitIterator() {
		return choiceUnit.listIterator();
	}

	public LPActivityUnit removeUnit(int index) {
		return choiceUnit.remove(index);
	}

	public boolean removeUnit(Object o) {
		return choiceUnit.remove(o);
	}

	public int sizeOfUnit() {
		return choiceUnit.size();
	}
	
	public void resetActivityUnits(){
		choiceUnit = new ArrayList<LPActivityUnit>();
	}
	
	/**
	 * Get all the activities for one specific category. The ID is the DB ID.
	 * @param categoryID category to get from
	 * @return ArrayList of Activities, empty if none in that category
	 */
	public ArrayList<LPActivity> getActivitiesForCategory(String categoryID){
		ArrayList<LPActivity> activityList = new ArrayList<LPActivity>();
		for(LPActivity activity : activities){
			if(activity.getCategory() == categoryID)
				activityList.add(activity);
		}
		return activityList;
	}
	
	/**
	 * Get all the activity-units for one specific category. The ID is the DB ID.
	 * @param categoryID category to get from
	 * @return ArrayList of activity-units, empty if none in that category
	 */
	public ArrayList<LPActivityUnit> getActivityUnitsForCategory(String categoryID){
		ArrayList<LPActivityUnit> activityList = new ArrayList<LPActivityUnit>();
		for(LPActivityUnit activity : choiceUnit){
			if(activity.getCategory() == categoryID)
				activityList.add(activity);
		}
		return activityList;
	}

	public ArrayList<LPActivity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<LPActivity> activities) {
		this.activities = activities;
	}

	public ArrayList<LPActivityUnit> getActivityUnits() {
		return choiceUnit;
	}

	public void setActivityUnits(ArrayList<LPActivityUnit> choiceUnit) {
		this.choiceUnit = choiceUnit;
	}

	@Override
	public IInstanciator createInstance() {
		LPProject project = new LPProject(this);
		return project;
	}
	
	@Override
	public boolean updateFromTransit(TransitLPProject transitEntity) {
		return helperUpdateFromTransit(transitEntity);
	}

	@Override
	public TransitLPProject createTransit() {
		TransitLPProject transit = new TransitLPProject();
		helperFillTransit(transit);
		return transit;
	}

}
