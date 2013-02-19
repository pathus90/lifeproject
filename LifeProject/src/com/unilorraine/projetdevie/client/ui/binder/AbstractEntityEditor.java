package com.unilorraine.projetdevie.client.ui.binder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.Composite;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;

public abstract class AbstractEntityEditor<T extends ITransitEntity> extends Composite implements
		Editor<T>, EntityEditor<T> {
	
	/**
	 * When overriding this constructor think about overriding the driver or you will get nice errors. 
	 */
	public AbstractEntityEditor() {
		driver = setDriver();
	}
	
	/**
	 * Provide the driver for the push and pull methods.
	 * @return The driver necessary to the 
	 */
	protected SimpleBeanEditorDriver <T, AbstractEntityEditor<T>> driver;
	
	/**
	 * Override this method to provide a driver.
	 * @return
	 */
	protected abstract SimpleBeanEditorDriver <T, AbstractEntityEditor<T>> setDriver();
	
	@Override
	public void push(T transitEntity) {
		driver.initialize(this);
		driver.edit(transitEntity);
	}
	
	
	@Override
	public T pull() {
		T transit =driver.flush();
		//task.setId(transitTasks.get(taskList.getSelectedIndex()).getId());
	    if (driver.hasErrors()) {
	      // TODO Check errors
	    }
	    System.out.println("ID of task : " + transit.getId());
	   return transit;
	}

}
