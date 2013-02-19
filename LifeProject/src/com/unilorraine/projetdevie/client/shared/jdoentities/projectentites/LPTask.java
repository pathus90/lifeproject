package com.unilorraine.projetdevie.client.shared.jdoentities.projectentites;

import javax.jdo.annotations.PersistenceCapable;

import com.unilorraine.projetdevie.client.shared.jdoentities.IInstanciator;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;

/**
 * JDO db class to represent and store a a Task wich is the smallest unit in a Life Project
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPTask extends AbstractLPProjectEntity<TransitLPTask> implements IInstanciator {
	
	
	/**
	 * Basic constructor
	 * @param name
	 * @param description
	 * @param imageLink
	 * @param status
	 */
	public LPTask(String name, String description, String imageLink,
			boolean status) {
		super(name, description, imageLink, status);
	}
	
	/**
	 * Copy constructor.  The progress and dates are not copied!
	 * @param task to be copied
	 */
	public LPTask(LPTask task) {
		super(task);
	}
	
	public LPTask(){
		super();
	}

	@Override
	public boolean updateFromTransit(TransitLPTask transitEntity) {
		return helperUpdateFromTransit(transitEntity);
	}

	@Override
	public TransitLPTask createTransit() {
		TransitLPTask transit = new TransitLPTask();
		helperFillTransit(transit);
		return transit;
	}

	@Override
	public LPTask createInstance() {
		LPTask task=  new LPTask(this);
		task.setSchemaID(this.getId());
		return task;
	}

}
