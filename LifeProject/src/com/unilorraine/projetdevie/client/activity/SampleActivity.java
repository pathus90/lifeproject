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
package com.unilorraine.projetdevie.client.activity;

import com.unilorraine.projetdevie.client.ClientFactory;
import com.unilorraine.projetdevie.client.place.SamplePlace;
import com.unilorraine.projetdevie.client.service.TaskService;
import com.unilorraine.projetdevie.client.service.TaskServiceAsync;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.unilorraine.projetdevie.client.ui.SampleView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class SampleActivity extends AbstractActivity implements SampleView.Presenter {
	/**
	 * Used to obtain views, eventBus, placeController.
	 * Alternatively, could be injected via GIN.
	 */
	private ClientFactory clientFactory;

	/**
	 * Sample property.
	 */
	private String name;
	
	private TaskServiceAsync taskSrv = GWT.create(TaskService.class);

	public SampleActivity(SamplePlace place, ClientFactory clientFactory) {
		this.name = place.getName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		SampleView view = clientFactory.getSampleView();
		view.setName(name);
		view.setPresenter(this);
		containerWidget.setWidget(view.asWidget());
	}

	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}

	/**
	 * @see SampleView.Presenter#goTo(Place)
	 */
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void load(String id) {
		 // Initialize the service proxy.
	    if (taskSrv == null) {
	    	taskSrv = GWT.create(TaskService.class);
	    }

	    // Set up the callback object.
	    AsyncCallback<TransitLPTask> callback = new AsyncCallback<TransitLPTask>() {
	      @Override
		public void onFailure(Throwable caught) {
	    	  System.out.println("Error in fetching Transit : " + caught.getMessage());
	      }

		@Override
		public void onSuccess(TransitLPTask result) {
			if(result != null){
				clientFactory.getSampleView().fillEditor(result);
			}else{
				System.out.println("Result came back as Null");
			}
			
		}

	      
	    };

	    // Make the call to the stock price service.
	   taskSrv.readEntity(id, callback);		
	}
}
