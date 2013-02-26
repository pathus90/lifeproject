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
import com.unilorraine.projetdevie.client.place.ApplicationPanelPlace;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.ApplicationPanelView;
import com.unilorraine.projetdevie.client.ui.ModuleListener;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.MenuModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule.PreparationModuleActivity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class ApplicationPanelActivity extends AbstractActivity implements ApplicationPanelView.Presenter, ModuleListener {
	/**
	 * Used to obtain views, eventBus, placeController.
	 * Alternatively, could be injected via GIN.
	 */
	private ClientFactory clientFactory;
	
	/**
	 * Fast access to the view for this controller
	 */
	private ApplicationPanelView view;
	
	/**
	 * The active app module
	 */
	private AppModule activeModule;
	
	/**
	 * The pending app module
	 */
	private AppModule pendingModule;

	/**
	 * Sample property.
	 */
	private String name;
	
	/**
	 * The app context, holds for example the user and the entities this app is linked with.
	 */
	private AppContext appContext;

	public ApplicationPanelActivity(ApplicationPanelPlace place, ClientFactory clientFactory) {
		this.name = place.getName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		ApplicationPanelView view = clientFactory.getApplicationPanelView();
		view.setName(name);
		view.setPresenter(this);
		view.setMenuTreeListener(this);
		this.view = view;
		containerWidget.setWidget(view.asWidget());
		
		
		connectModule(new PreparationModuleActivity());
	}

	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}

	/**
	 * @see ApplicationPanelView.Presenter#goTo(Place)
	 */
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
		
	}

	@Override
	public void onSelection(SelectionEvent<TreeItem> event) {
		if(activeModule instanceof MenuModule)
			((MenuModule)activeModule).handleMenuAction(event);
		
	}

	@Override
	public void connectModule(AppModule module) {
		//we set the incoming module to pending state
		this.pendingModule = module;
		
		//We listen to this module
		pendingModule.setModuleListener(this);
		
		//We set the context
		pendingModule.setAppContext(appContext);
		
		//we wait till the active module call the moduleDestroyReady, when destroy we start the pending one
		//we bypass if there no active menu
		if(activeModule != null)
			this.activeModule.onDestroy();
		else{
			moduleDestructionReady();
		}
			
		
	}

	@Override
	public void setAppContext(AppContext context) {
		this.appContext = context; 
	}

	@Override
	public void moduleDisplayReady() {
		
		// set the view
		view.setAppModuleView(activeModule.getWidget());
		
		//set the menu
		if(activeModule instanceof MenuModule)
			view.setAppMenuItems(((MenuModule)activeModule).getMenuItems());
	}
	
	@Override
	public AppContext getAppContext() {
		return appContext;
	}


	@Override
	public void moduleDestructionReady() {
		
		//we stop listening
		if(activeModule != null)
			activeModule.setModuleListener(null);
		
		//we make the switch if there is a pending
		if(pendingModule != null){
			
			//switch
			activeModule = pendingModule;
			pendingModule = null;
			
			//we launch the start sequence
			activeModule.onStart();
		}else{
			emptyModule();
		}
		
	}

	@Override
	public void emptyModule() {
		activeModule = null;
		view.emptyModule();
	}

	@Override
	public void gotoDefaultMenu() {
		PreparationModuleActivity module = new PreparationModuleActivity();
		module.setAppContext(appContext);
		connectModule(module);
		
	}
}
