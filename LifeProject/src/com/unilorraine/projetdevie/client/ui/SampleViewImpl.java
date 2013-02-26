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
package com.unilorraine.projetdevie.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.unilorraine.projetdevie.client.place.ApplicationPanelPlace;
import com.unilorraine.projetdevie.client.place.CategorySelectionPlace;
import com.unilorraine.projetdevie.client.service.ActivityService;
import com.unilorraine.projetdevie.client.service.ActivityServiceAsync;
import com.unilorraine.projetdevie.client.service.TaskService;
import com.unilorraine.projetdevie.client.service.helperinterfaces.DBTestMaterialService;
import com.unilorraine.projetdevie.client.service.helperinterfaces.DBTestMaterialServiceAsync;
import com.unilorraine.projetdevie.client.service.init.DBInitService;
import com.unilorraine.projetdevie.client.service.pots.PotCategoryService;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;
import com.unilorraine.projetdevie.client.ui.binder.TaskEditorBinder;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;
import com.unilorraine.projetdevie.server.service.init.DBInitServiceImpl;

/**
 * Sample implementation of {@link SampleView}.
 */
public class SampleViewImpl extends Composite implements SampleView {

	  interface Driver extends SimpleBeanEditorDriver<TransitLPTask, TaskEditorBinder> {}
		ActivityServiceAsync activitySrv = GWT.create(ActivityService.class);
	
	  // Create the Driver
	  Driver driver = GWT.create(Driver.class);
	
	interface Binder extends UiBinder<Widget, SampleViewImpl> {
	}
	
	private static final Binder binder = GWT.create(Binder.class);
	@UiField Button button;

	private Presenter listener;

	public SampleViewImpl() {
		initWidget(binder.createAndBindUi(this));
	}

	@UiFactory TaskEditorBinder makeTaskEditorBinder() { // method name is insignificant
		return new TaskEditorBinder("aglub19hcHBfaWRyEAsSCkxQQWN0aXZpdHkYGgw", null);
	  
	}
	
	@Override
	public void setName(String name) {
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}
	
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {

		 listener.initDB();
	}
	
	//Init event
	/*
	@UiHandler("button")
	void onButtonClick(ClickEvent event) {

	    // Set up the callback object.
	    AsyncCallback<Void> callback = new AsyncCallback<Void>() {
		      @Override
			public void onFailure(Throwable caught) {
		    	  System.out.println("Error in populating db");
		      }

			@Override
			public void onSuccess(Void result) {
				System.out.println("Should have been populated");
			}

			
	    };

	    // Make the call to the stock price service.
	    DBInitService.Util.getInstance().initMethod(callback);
	}
	*/
	
	/*
	//Prepare the callback
					AsyncCallback<List<TransitLPPot<TransitLPCategory>>> callback = new AsyncCallback<List<TransitLPPot<TransitLPCategory>>>() {

						@Override
						public void onFailure(Throwable caught) {
							System.err.println("Error while fetching categories : " + caught.getMessage());
							
						}

						@Override
						public void onSuccess(List<TransitLPPot<TransitLPCategory>> result) {
							if (result == null)
								System.err.println("Server Error while fetching categories");
							else{
								
								
								//display the records
								
								ArrayList<CategoryRecord> records = new ArrayList<CategoryRecord>();
								for(TransitLPPot<TransitLPCategory> pot : result){
									for(TransitLPCategory cat : pot.getStoredEntities()){
										records.add(new CategoryRecord(cat));
									}
								}
								CategoryRecord[] cat = new CategoryRecord[records.size()];
								System.out.println("Successfully fetched categories size : " + records.size());
								cat = records.toArray(cat);
								System.out.println("transfo : " + cat.length);
								for(int i = 0; i < cat.length; i++){
									System.out.println("Cat " + i + " " + cat[i].getName());
								}
								
							}
							
						}
					};
					//Call the pot service and fetch the categories
					PotCategoryService.Util.getInstance().getTransitPotsByLink(result.getId(), callback);
	 */
	
	@Override
	public void fillEditor(TransitLPTask transitTask) {
		//driver.initialize(task);
		//driver.edit(transitTask);
		
	}
}
