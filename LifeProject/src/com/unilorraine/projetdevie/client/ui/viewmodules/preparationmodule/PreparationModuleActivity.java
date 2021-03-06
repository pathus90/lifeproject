/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.unilorraine.projetdevie.client.service.ActivityService;
import com.unilorraine.projetdevie.client.service.ActivityUnitService;
import com.unilorraine.projetdevie.client.service.ProjectService;
import com.unilorraine.projetdevie.client.service.pots.PotActivityService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppMenuModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects.UnitPanel;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects.UnitTileGrid;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class PreparationModuleActivity extends AbstractAppMenuModule implements PreparationModuleView.Presenter {

	//TODO view information shouldn't be set here
	private static final String MODULE_NAME = "Pr\u00E9paration du LP";
	private static final String MODULE_PIC_LINK = "";
	
	private static final String CATEGORY_MENU = "Autre cat\u00E9gorie";
	
	
	private PreparationModuleView view;
	
	
	private TransitLPCategory transitCategory;
	
	private String institutionID;
	
	private String projectID;
	
	private ArrayList<String> takenActivities;
	
	private int transitFetcherCounter = 0;
	
	private int saverCount = 0;
	
	//Note that we don't actually create anything in the constructor. 
	//By doing so we embrace the possibiliy of using async fetching without white screen delay for the user.
	public PreparationModuleActivity() {
		super();
		setModuleName(MODULE_NAME);
	}
	
	private void fetcherReady(){
		System.out.println("fetcherReady");
		transitFetcherCounter--;
		if(transitFetcherCounter <= 0){
			new FetchSchemaActivities();
		}
	}
	
	private void saved() {
		saverCount--;
		if(saverCount <= 0){
			view.showSaved();
		}
		
	}
	
	@Override
	public void onStart() {
		System.out.println("In start");
		view = new PreparationModuleViewImpl();//(PreparationModuleView)GWT.create(PreparationModuleView.class);
		view.setPresenter(this);
		view.setCategory(transitCategory.getName());
		institutionID = getAppContext().getActiveAccount().getInstitutions().get(0);
		projectID = getAppContext().getContextLinks().get(2);
		//The menu creation
		intiMenu();
		
		
		takenActivities = new ArrayList<String>();
		new FetchProjectActivites();
		
	}
	
	/**
	 * Initialize the menu
	 */
	private void intiMenu() {
		
		removeAll();
		
		//Item 1 sub-tree
		TreeItem item1 = new TreeItem();
		
		item1.setText(SafeHtmlUtils.htmlEscape(CATEGORY_MENU));
		item1.setUserObject(CATEGORY_MENU);
		
		addItem(item1);	
		
	}

	//TODO to implement
	@Override
	public void handleMenuAction(SelectionEvent<TreeItem> event) {
		TreeItem item = event.getSelectedItem();
		if(item.getUserObject().equals(CATEGORY_MENU)){
			getModuleListener().connectModule(new CategoryModuleActivity());
		}
	}

	@Override
	public Widget getWidget() {
		if(view != null)
			return view.asWidget();
		else{
			System.err.println("The PreparationModuleView is not instantiated!");
			return null;
		} 
	}

	@Override
	public void setCategory(TransitLPCategory transitCategory) {
		this.transitCategory = transitCategory;
		
	}

	@Override
	public TransitLPCategory getCategory() {
		return transitCategory;
	}

	/**
	 * @return the view
	 */
	private final PreparationModuleView getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	private final void setView(PreparationModuleView view) {
		this.view = view;
	}

	@Override
	public void needNewGrid() {
		if(this.view != null)
			this.view.addNewUnitChoice();
	}

	@Override
	public void deleteGrid(UnitPanel unitPanel) {
		this.view.removeGrid(unitPanel);
		
	}
	
	@Override
	public void saveProject() {
		saverCount = 2;
		
		new SaveProjectActivities(getActivities());
		new SaveProjectActivityUnits(getActivityUnits());
		
	}
	
	private List<TransitLPActivity> getActivities(){
		ArrayList<TransitLPActivity> activities = new ArrayList<TransitLPActivity>();
		
		for(Record record : view.getProjectActivities()){
			TransitLPActivity transit = (TransitLPActivity)record.getAttributeAsObject("transit");
			activities.add(transit);
		}
		
		return activities;
		
	}
	
	private List<TransitLPActivityUnit> getActivityUnits(){
		ArrayList<TransitLPActivityUnit> activityUnit = new ArrayList<TransitLPActivityUnit>();
		System.out.println("Size : " + view.getUnitChoices().size());
		
		for(List<Record> unitRecord : view.getUnitChoices()){
			TransitLPActivityUnit unit = new TransitLPActivityUnit();
			unit.setCategory(transitCategory.getId());
			
			for(Record record : unitRecord ){
				System.out.println("CHOICe activity to be added : " + ((TransitLPActivity)record.getAttributeAsObject("transit")).getName());
				unit.addActivity(((TransitLPActivity)record.getAttributeAsObject("transit")).getId());
			}
			
			activityUnit.add(unit);
			
		}

		return activityUnit;
		
	}
	
	/**
	 * Async answer to Fetch all the activities in the proect
	 * @author Christophe
	 *
	 */
	private class FetchProjectActivites implements  AsyncCallback<ArrayList<TransitLPActivity>>{
		
		public FetchProjectActivites() {
			ProjectService.Util.getInstance().getActivitesForCategory(projectID, transitCategory.getId(), this);
		}
		
		@Override
		public void onSuccess(ArrayList<TransitLPActivity> result) {
			if(result != null){
				ActivityRecord[] records;
				List<ActivityRecord> listRecords = new ArrayList<ActivityRecord>();
					
				for(TransitLPActivity activity : result){
					if(activity.getCategory().equals(transitCategory.getId())){
						System.out.println("activity found " + activity.getName());
						listRecords.add(new ActivityRecord(activity));
						
						
						if(!activity.getSchemaID().equals(""))
							takenActivities.add(activity.getSchemaID());
					}
					
				}
				records = new ActivityRecord[listRecords.size()];
				records = listRecords.toArray(records);
				
				view.setProjectActivities(records);
				System.out.println("FetchProjectActivites");
				new FetchProjectActivityUnits();
			}else{
				System.err.println("Error while fetching project activities");
			}
			
		}
		
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error while fetching project activities");
			//TODO HANDLE ERROR
		}
	}
	
	/**
	 * Answer to fetch all the activity units in the project
	 * @author Christophe
	 *
	 */
	private class FetchProjectActivityUnits implements  AsyncCallback<ArrayList<TransitLPActivityUnit>>{
			
		public FetchProjectActivityUnits() {
			ProjectService.Util.getInstance().getActivityUnitsForCategory(projectID, transitCategory.getId(), this);
			
		}
		
			@Override
			public void onSuccess(ArrayList<TransitLPActivityUnit> result) {
				if(result != null){
					transitFetcherCounter = result.size();
					if(transitFetcherCounter == 0)
						fetcherReady();
					for(TransitLPActivityUnit activityUnit : result){
						new FetchUnitTransits(activityUnit.getId(), activityUnit.getActivityUnit());
						System.out.println("FetchProjectActivityUnits");
						
					}
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Error while fetching project activities");
				
			}
	}
	
	private class FetchSchemaActivities implements AsyncCallback<List<TransitLPPot<TransitLPActivity>>>{
			
		public FetchSchemaActivities() {
			PotActivityService.Util.getInstance().getTransitPotsByLink(institutionID, this);
		}
		
		@Override
		public void onSuccess(List<TransitLPPot<TransitLPActivity>> result) {
			if(result != null){
				ActivityRecord[] records;
				List<ActivityRecord> listRecords = new ArrayList<ActivityRecord>();
				for(TransitLPPot<TransitLPActivity> pot : result){
					for(TransitLPActivity activity : pot.getStoredEntities()){
						if(activity.getCategory().equals(transitCategory.getId()) && !takenActivities.contains(activity.getId()))
							listRecords.add(new ActivityRecord(activity));
					}
				}
				records = new ActivityRecord[listRecords.size()];
				records = listRecords.toArray(records);
				
				view.setSchemaActivities(records);
				view.addNewUnitChoice();
				System.out.println("FetchSchemaActivities");
				fireDisplayReady();
			}else
				System.err.println("Error Fetching activities");
			
		}
		
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error while fetching schema activities");
			//TODO HANDLE ERROR
			
		}
		
	}
	
	private class FetchUnitTransits implements AsyncCallback<List<TransitLPActivity>>{
		
		String unitId;
		
		public FetchUnitTransits(String unitId, List<String>activitiesToFind) {
			this.unitId = unitId;
			ActivityService.Util.getInstance().readEntities(activitiesToFind, this);
		}
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error while fetching project units");
			fetcherReady();
			//TODO HANDLE ERROR
			
		}

		@Override
		public void onSuccess(List<TransitLPActivity> result) {
			if(result != null){
				System.out.println("Unit");
				ActivityRecord[] records;
				List<ActivityRecord> listRecords = new ArrayList<ActivityRecord>();
				
				for(TransitLPActivity activity : result){
					if(activity.getCategory().equals(transitCategory.getId())){
						listRecords.add(new ActivityRecord(activity));
						
						System.out.println("activity found unit : " + activity.getName() + " id " + activity.getId());
						if(!activity.getId().equals(""))
							takenActivities.add(activity.getId());
					}
					
				}
				
				records = new ActivityRecord[listRecords.size()];
				records = listRecords.toArray(records);
				view.addExistingUnitChoice(unitId, records);
			}
			fetcherReady();
			
		}
		
	}
	
	private class SaveProjectActivities implements AsyncCallback<TransitLPProject>{
	
			
			public SaveProjectActivities(List<TransitLPActivity>activitiesToSave) {
				ProjectService.Util.getInstance().setActivitiesFromCategory(projectID, transitCategory.getId(), activitiesToSave, this);
			}
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Error while saving the project activities");
				//TODO HANDLE ERROR
				
			}
	
			@Override
			public void onSuccess(TransitLPProject result) {
				if(result != null){
					System.out.println("savig activities went great!");
					saved();
				}else
					System.err.println("Error while saving the project Activities");
					
				
			}
		
	}
	
	private class SaveProjectActivityUnits implements AsyncCallback<TransitLPProject>{
		
		
		public SaveProjectActivityUnits(List<TransitLPActivityUnit>activitiesToSave) {
			ProjectService.Util.getInstance().setActivityUnitsFromCategory(projectID, transitCategory.getId(), activitiesToSave, this);
		}
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error while saving the project activities");
			//TODO HANDLE ERROR
			
		}

		@Override
		public void onSuccess(TransitLPProject result) {
			if(result != null){
				System.out.println("saving activities went great!");
				saved();
			}else
				System.err.println("Error while saving the project Activities");
				
			
		}
	
}
	
}
