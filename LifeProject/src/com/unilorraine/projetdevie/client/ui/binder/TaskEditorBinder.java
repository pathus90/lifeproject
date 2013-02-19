package com.unilorraine.projetdevie.client.ui.binder;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.IntegerBox;
import com.unilorraine.projetdevie.client.service.ActivityService;
import com.unilorraine.projetdevie.client.service.ActivityServiceAsync;
import com.unilorraine.projetdevie.client.service.TaskService;
import com.unilorraine.projetdevie.client.service.TaskServiceAsync;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ListBox;

public class TaskEditorBinder extends Composite implements HasText, Editor<TransitLPTask> {

	private static TaskEditorBinderUiBinder uiBinder = GWT
			.create(TaskEditorBinderUiBinder.class);
	
	  interface Driver extends SimpleBeanEditorDriver<TransitLPTask, TaskEditorBinder> {}
		
	  // Create the Driver
	  Driver driver = GWT.create(Driver.class);
	  
	
	private TaskServiceAsync taskSrv = GWT.create(TaskService.class);
	private ActivityServiceAsync activitySrv = GWT.create(ActivityService.class);
	
	private ArrayList<TransitLPTask> transitTasks = new ArrayList<TransitLPTask>();
	
	private String activityId;
	
	DialogBox box = new DialogBox();
	
	
	@UiField TextArea description;
	@UiField TextBox name;
	@UiField TextBox imageLink;
	@UiField IntegerBox progress;

	interface TaskEditorBinderUiBinder extends
			UiBinder<Widget, TaskEditorBinder> {
	}

	public TaskEditorBinder() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public @UiConstructor TaskEditorBinder(String activityId, ArrayList<TransitLPTask> transitTasks) {
		initWidget(uiBinder.createAndBindUi(this));
		if(transitTasks != null)
			this.transitTasks = transitTasks;
		this.activityId = activityId;
		
		/*
		taskList.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				fillEditor(taskList.getSelectedIndex());
				
			}
		});
		
		
		init();
		*/
	}

	/*
	private void init() {
		box.setText("Loading...");
		
		for(TransitLPTask task : transitTasks){
			taskList.addItem(task.getName());
		}
		if(taskList.getItemCount() > 0){
			taskList.setSelectedIndex(0);
			fillEditor(0);
		}else{
			isEmpty();
		}
		
	}

*/
	@Override
	public String getText() {
		return null;
	}

	@Override
	public void setText(String text) {
		
	}
	
	/*
	private void fillEditor(int taskIndex) {
		driver.initialize(this);
		driver.edit(transitTasks.get(taskIndex));
		
	}
	
	private TransitLPTask updload(){
		TransitLPTask task = driver.flush();
		task.setId(transitTasks.get(taskList.getSelectedIndex()).getId());
	    if (driver.hasErrors()) {
	      // TODO Check errors
	    }
	    System.out.println("ID of task : " + task.getId());
	   return task;
	}
	
	// TODO We schould disable the complet widget except add button! Thats we encapsulated it
	private void isEmpty(){
		update.setEnabled(false);
	}
	
	// TODO We schould disable the complet widget except add button! Thats we encapsulated it
	private void isNotEmtpy(){
		update.setEnabled(true);
	}
	
	private void addTransitTask(TransitLPTask task){
		if(task != null){
			transitTasks.add(task);
			int index = transitTasks.indexOf(task);
			taskList.addItem(task.getName());
			taskList.setSelectedIndex(index);
			fillEditor(index);
			isNotEmtpy();
		}
		
	}
	*/
	
}
