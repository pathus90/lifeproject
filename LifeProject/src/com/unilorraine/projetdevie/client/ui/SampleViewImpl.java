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
import com.unilorraine.projetdevie.client.service.ActivityService;
import com.unilorraine.projetdevie.client.service.ActivityServiceAsync;
import com.unilorraine.projetdevie.client.service.TaskService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.unilorraine.projetdevie.client.ui.binder.TaskEditorBinder;

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
	
		 // Initialize the service proxy.
	    if (activitySrv == null)
	    	activitySrv = GWT.create(TaskService.class);

	    // Set up the callback object.
	    AsyncCallback<TransitLPActivity> callback = new AsyncCallback<TransitLPActivity>() {
		      @Override
			public void onFailure(Throwable caught) {
		    	  System.out.println("Error in creating Transit : " + caught.getMessage());
		      }

			@Override
			public void onSuccess(TransitLPActivity result) {
				if(result != null){
					System.out.println("ID of created Activity " + result.getId());
				}else{
					System.out.println("Result came back as Null");
				}
			}
	    };

	    // Make the call to the stock price service.
	    activitySrv.createEntity(callback);
	}

	@Override
	public void fillEditor(TransitLPTask transitTask) {
		//driver.initialize(task);
		//driver.edit(transitTask);
		
	}
}
