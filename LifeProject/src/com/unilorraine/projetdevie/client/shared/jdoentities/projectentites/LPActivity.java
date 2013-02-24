package com.unilorraine.projetdevie.client.shared.jdoentities.projectentites;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.jdoentities.IInstanciator;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;

/**
 * JDO db class to represent and store a an Activity. An activity is composed of {@link LPTask}
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPActivity extends AbstractLPProjectEntity<TransitLPActivity> implements IInstanciator {

	/**
	 * The list of embeded Tasks in an Activity
	 */
	@Persistent
	private ArrayList<LPTask> tasks;
	
	/**
	 * The category the activity is in
	 */
	@Persistent
	private String category;
	
	/**
	 * Basic constructor for an activity. The Task list is initialised and empty
	 * @param name
	 * @param description
	 * @param imageLink
	 * @param status
	 */
	
	public LPActivity(String name, String description, String imageLink,
			boolean status, String category) {
		super(name, description, imageLink, status);
		this.category = category;
		tasks = new ArrayList<LPTask>();
	}
	
	/**
	 * Copy constructor. The progress and dates are not copied!
	 * @param activity
	 */
	public LPActivity(LPActivity activity){
		super(activity);
		this.category = activity.category;
		tasks = new ArrayList<LPTask>();
		for(LPTask task : activity.tasks){
			tasks.add(new LPTask(task));
		}
		
	}
	
	public LPActivity() {
		this("","", "", false, null);
	}
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTasks(ArrayList<LPTask> tasks) {
		this.tasks = tasks;
	}
	
	public ArrayList<LPTask> getTasks() {
		return tasks;
	}

	public boolean addTask(LPTask task) {
		return tasks.add(task);
	}

	public LPTask getTask(int index) {
		return tasks.get(index);
	}

	public int indexOfTask(LPTask task) {
		return tasks.indexOf(task);
	}

	public LPTask removeTask(int index) {
		return tasks.remove(index);
	}

	public boolean removeTask(LPTask task) {
		return tasks.remove(task);
	}

	
	public int sizeOfTasks() {
		return tasks.size();
	}

	public ListIterator<LPTask> tasksIterator() {
		return tasks.listIterator();
	}

	@Override
	public LPActivity createInstance() {
		LPActivity activity = new LPActivity(this);
		return activity;
	}

	@Override
	public boolean updateFromTransit(TransitLPActivity transitEntity) {
		helperUpdateFromTransit(transitEntity);
		setCategory(transitEntity.getCategory());
		return false;
	}

	@Override
	public TransitLPActivity createTransit() {
		TransitLPActivity transit = new TransitLPActivity();
		helperFillTransit(transit);
		transit.setCategory(this.getCategory());
		return transit;
	}

}
