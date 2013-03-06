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
package com.unilorraine.projetdevie.client.ui.viewmodules.unitchoosermodule;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.smartgwt.client.widgets.tile.events.RecordDoubleClickEvent;
import com.unilorraine.projetdevie.client.service.ActivityService;
import com.unilorraine.projetdevie.client.service.ActivityUnitService;
import com.unilorraine.projetdevie.client.service.ProjectService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivityUnit;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule.CategoryModuleView;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class UnitChooserActivity extends AbstractAppModule implements UnitChooserView.Presenter {

	private UnitChooserView view = null;
	
	private int transitFetcherCounter = 0;
	
	private String projectID;
	
	private List<TransitLPActivityUnit> units;
	
	private String actualUnit;
	
	public UnitChooserActivity() {
		setModuleName("Createur");
		setModulePictureLink("http://openclipart.org/image/128px/svg_to_png/89299/mb-cart-96x96.png");
	}
	
	/* (non-Javadoc)
	 * @see com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppModule#onStart()
	 */
	@Override
	public void onStart() {
		if(view == null){
			view = new UnitChooserViewImpl();
			view.setPresenter(this);
		}
		projectID = getAppContext().getContextLinks().get(2);
		
		transitFetcherCounter = 2;
		new FetchProjectActivites();
		new FetchProjectActivityUnits();
	}
	
	private void fetcherReady(){
		System.out.println("fetcherReady");
		transitFetcherCounter--;
		if(transitFetcherCounter <= 0){
			super.onStart();
			fetchNextUnit();
		}
	}

	private void fetchNextUnit() {
		if(units.size() > 0){
			actualUnit= units.get(0).getId();
			units.remove(0);
			view.showLoading();
			new FetchProjectActivityUnit(actualUnit);
		}else{
			view.showNoMoreUnitsDialog();
			view.resest();
		}
		
	}
	
	private void unitReady(){
		view.hideLoading();
	}

	@Override
	public Widget getWidget() {
		if(view != null)
			return view.asWidget();
		else
			return null;
	}

	@Override
	public void onRecordDoubleClick(RecordDoubleClickEvent event) {
		String activityID = ((TransitLPActivity)view.getTargetRecord().getAttributeAsObject(ActivityRecord.ACTIVITY_ATTRIBUTE)).getId();
		new CommitUnit(actualUnit, activityID);
		fetchNextUnit();
	}
	
	/**
	 * Async answer to Fetch all the activities in the proect
	 * @author Christophe
	 *
	 */
	private class FetchProjectActivites implements  AsyncCallback<ArrayList<TransitLPActivity>>{
		
		public FetchProjectActivites() {
			ProjectService.Util.getInstance().getAllActivities(projectID, this);
		}
		
		@Override
		public void onSuccess(ArrayList<TransitLPActivity> result) {
			if(result != null){
				ActivityRecord[] records;
				List<ActivityRecord> listRecords = new ArrayList<ActivityRecord>();
					
				for(TransitLPActivity activity : result){
					
					System.out.println("activity found " + activity.getName());
					listRecords.add(new ActivityRecord(activity));
				}
				records = new ActivityRecord[listRecords.size()];
				records = listRecords.toArray(records);
				
				view.setActivities(records);

				fetcherReady();
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
			ProjectService.Util.getInstance().getAllActivityUnits(projectID, this);
			
		}
		
			@Override
			public void onSuccess(ArrayList<TransitLPActivityUnit> result) {
				if(result != null){
					units = result;
					fetcherReady();
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Error while fetching project activities");
				
			}
	}
	
	/**
	 * Answer to fetch all the activity units in the project
	 * @author Christophe
	 *
	 */
	private class FetchProjectActivityUnit implements  AsyncCallback<TransitLPActivityUnit>{
			
		public FetchProjectActivityUnit(String unitID) {
			ActivityUnitService.Util.getInstance().readEntity(unitID, this);
			
		}
		
			@Override
			public void onSuccess(TransitLPActivityUnit result) {
				if(result != null){
						new FetchUnitTransits(result.getId(), result.getActivityUnit());
				}
				else{
					System.err.println("Error while fetching unit");
				}
					
			}
			
			@Override
			public void onFailure(Throwable caught) {
				System.err.println("Error while fetching unit");
				
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
				System.err.println("Error while fetching project activities of unit");
				fetcherReady();
				//TODO HANDLE ERROR
				
			}
	
			@Override
			public void onSuccess(List<TransitLPActivity> result) {
				if(result != null){
					ActivityRecord[] records;
					List<ActivityRecord> listRecords = new ArrayList<ActivityRecord>();
					
					for(TransitLPActivity activity : result){
							listRecords.add(new ActivityRecord(activity));
					}
					
					records = new ActivityRecord[listRecords.size()];
					records = listRecords.toArray(records);
					view.setUnitContainer(records);
				}
				unitReady();
				
			}
			
		}
	
	private class CommitUnit implements AsyncCallback<TransitLPActivity>{
		String unitID;
		
		public CommitUnit(String unitID, String activityID) {
			this.unitID = unitID;
			ProjectService.Util.getInstance().commitActivityUnit(projectID, unitID, activityID, this);
		}

		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error commiting the unit : " + unitID);
			
		}

		@Override
		public void onSuccess(TransitLPActivity result) {
			if(result != null){
				view.addActivity(new ActivityRecord(result));
			}else
				System.err.println("Error commiting the unit : " + unitID);
			
		}
	}

	

}
