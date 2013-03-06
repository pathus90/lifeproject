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

import java.util.ArrayList;
import java.util.List;

import com.unilorraine.projetdevie.client.ClientFactory;
import com.unilorraine.projetdevie.client.place.ApplicationPanelPlace;
import com.unilorraine.projetdevie.client.place.SamplePlace;
import com.unilorraine.projetdevie.client.service.TaskService;
import com.unilorraine.projetdevie.client.service.TaskServiceAsync;
import com.unilorraine.projetdevie.client.service.init.DBInitService;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.SampleView;
import com.unilorraine.projetdevie.server.service.init.DBInitServiceImpl;

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
		
		initDB();
	}

	 public void initDB() {
		
		AsyncCallback<List<ITransitEntity>> callback = new AsyncCallback<List<ITransitEntity>>() {
		      @Override
			public void onFailure(Throwable caught) {
		    	  System.out.println("Error in populating db");
		      }

			@Override
			public void onSuccess(List<ITransitEntity> result) {
				//TODO we are so cheating here...
				
				System.out.println("Should have been populated");
				TransitLPInstitution institution = (TransitLPInstitution)result.get(0);
				TransitLPActor actor = (TransitLPActor)result.get(1);
				TransitLPUser user = (TransitLPUser)result.get(2);
				TransitLPProject project = (TransitLPProject)result.get(3);
				
				ApplicationPanelPlace place = new ApplicationPanelPlace("Application Panel");
				List<String> links = new ArrayList<String>();
				links.add(institution.getId());
				links.add(user.getId());
				links.add(project.getId());
				place.setAppContext(new AppContext(actor, links));
				goTo(place);
			}

			
	    };

	    // Make the call to the stock price service.
	    DBInitService.Util.getInstance().initMethod(callback);
		
	}

	 /*
	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}
	*/

	/**
	 * @see SampleView.Presenter#goTo(Place)
	 */
	@Override
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}	
}
