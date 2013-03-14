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

import org.apache.catalina.startup.SetDocBaseRule;

import com.unilorraine.projetdevie.client.ClientFactory;
import com.unilorraine.projetdevie.client.place.ApplicationPanelPlace;
import com.unilorraine.projetdevie.client.service.LoginService;
import com.unilorraine.projetdevie.client.service.ProjectService;
import com.unilorraine.projetdevie.client.service.UserService;
import com.unilorraine.projetdevie.client.service.init.DBInitService;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.LoginInfo;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.ApplicationPanelView;
import com.unilorraine.projetdevie.client.ui.ModuleListener;
import com.unilorraine.projetdevie.client.ui.tilerecord.ModuleRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.MenuModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.apphandlermodule.ModuleHandlerActivity;
import com.unilorraine.projetdevie.client.ui.viewmodules.apphandlermodule.ModuleHandlerView;
import com.unilorraine.projetdevie.client.ui.viewmodules.apphandlermodule.ModuleHandlerView.Presenter;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.CategoryModuleActivity;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.PreparationModuleActivity;
import com.unilorraine.projetdevie.client.ui.viewmodules.unitchoosermodule.UnitChooserActivity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

/**
 * The controller implementation of {@link ApplicationPanelView.Presenter}. See {@link ApplicationPanelView} for more details. 
 */
public class ApplicationPanelActivity extends AbstractActivity implements ApplicationPanelView.Presenter {
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
	
	/**
	 * TODO this is just for presentation, should be removed
	 * The user connecting
	 */
	private TransitLPUser user;
	
	/**
	 * TODO this is just for presentation, should be removed
	 * The buidling project
	 */
	private TransitLPProject project;
	
	/**
	 * The module handler which switches between modules
	 */
	private ModuleHandlerView.Presenter moduleHandler;

	public ApplicationPanelActivity(ApplicationPanelPlace place, ClientFactory clientFactory) {
		this.name = place.getName();
		this.clientFactory = clientFactory;
		
		moduleHandler = new ModuleHandlerActivity();
		moduleHandler.setModules(moduleReferences());
	}


	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		ApplicationPanelView view = clientFactory.getApplicationPanelView();
		view.setName(name);
		
		//Nos 15 listeners...
		view.setPresenter(this);
		view.setPresenter(this);
		view.setMenuTreeListener(this);
		
		
		this.view = view;
		System.out.println("Login should be schown");
		//view.showLogin();
		//try to get the login information
		loginAttempt();
		containerWidget.setWidget(view.asWidget());
	}

	private void loginAttempt() {
		new Login();
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
		
		//let the user now it could take a while
		view.waitingMouse(true);
		
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
		
		//set the menu or empties it
		if(activeModule instanceof MenuModule)
			view.setAppMenuItems(((MenuModule)activeModule).getMenuItems());
		else
			view.emptyMenu();
		//let the user now it could take a while
		view.waitingMouse(false);
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
	public void connectModuleHandler() {
		connectModule(moduleHandler);
	}


	@Override
	public List<RegisterableModule> moduleReferences() {
		
		List<RegisterableModule> modules = new ArrayList<RegisterableModule>();
		
		//Add the modules here
		//The preparation module
		CategoryModuleActivity category = new CategoryModuleActivity();
		modules.add(category);
		
		//Add the unit chooser module
		UnitChooserActivity chooser = new UnitChooserActivity();
		modules.add(chooser);
		
		/*
		//The preparation module
		PreparationModuleActivity prepModule3 = new PreparationModuleActivity();
		prepModule3.setAppContext(appContext);
		modules.add(prepModule3);
		*/
		
		return modules;
	}


	@Override
	public void redraw(Widget widget) {
		view.setAppModuleView(widget);
	}


	@Override
	public void setSelectedItem(TreeItem item, boolean fireEvents) {
		view.setSelectedItem(item, fireEvents);
	}

	@Override
	public void redraw() {
		if(activeModule !=null)
			view.setAppModuleView(activeModule.getWidget());
		
	}


	@Override
	public Presenter getModuleHandler() {
		return moduleHandler;
	}


	@Override
	public void setModuleHandler(ModuleHandlerView.Presenter handler) {
		moduleHandler = handler;
		
	}
	
	private void initDB() {
		
		view.waitingPopup(true);
		
		AsyncCallback<List<ITransitEntity>> callback = new AsyncCallback<List<ITransitEntity>>() {
		      @Override
			public void onFailure(Throwable caught) {
		    	  System.out.println("Error in populating db");
		      }

			@Override
			public void onSuccess(List<ITransitEntity> result) {
				//TODO we are so cheating here...
				if(result != null){
					System.out.println("Should have been populated");
					TransitLPInstitution institution = (TransitLPInstitution)result.get(0);
					TransitLPActor actor = (TransitLPActor)result.get(1);
					//TransitLPUser user = (TransitLPUser)result.get(2);
					//TransitLPProject project = (TransitLPProject)result.get(3);
					
					List<String> links = new ArrayList<String>();
					links.add(institution.getId());
					links.add(user.getId());
					links.add(project.getId());
					setAppContext((new AppContext(actor, links)));
					
					connectModuleHandler();
					
					view.waitingPopup(false);
					System.out.println("Test");
					
				}else
					System.err.println("Some error happend while init DB");
			}

			
	    };
	    
	    DBInitService.Util.getInstance().initMethod(callback);
	}

	
	private void readyToLaunch(){
		//view.hideLogin();
		view.createView();
		initDB();
	}

	@Override
	public void login(String email) {
		new UserLogin(email);
	}
	
	
	private class UserLogin implements AsyncCallback<TransitLPUser>{

		String email;
		public UserLogin(String email){
			
			this.email = email;
			
			UserService.Util.getInstance().getUserForEmail(email, this);		
		}
		
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error in User fetching" + caught.getMessage());
		}
	
	
		@Override
		public void onSuccess(TransitLPUser result) {
			if(result != null){
				user = result;
				//TODO fetch project
				new FetchProject(result.getBuildingProject());
			}else{
				System.out.println("New User needed");
				new CreateNewUser(this.email);
			}
			
		}
	}
	
	private class CreateNewUser implements AsyncCallback<TransitLPUser>{

		public CreateNewUser(String email){
			UserService.Util.getInstance().createEntity(
					new TransitLPUser("", 
							email, 
							email, 
							email, 
							"", 
							"", 
							"", 
							""), this);
		}
		
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error on user creation");	
		}

		@Override
		public void onSuccess(TransitLPUser result) {
			if(result != null){
				user = result;
				if(result.getBuildingProject() != null ){
					if(!result.getBuildingProject().equals("")){
						new FetchProject(result.getBuildingProject());
					}else
						new CreateProject(result.getId());
				}else
					new CreateProject(result.getId());
			}
			
		}
		
	}
	
	private class FetchProject implements AsyncCallback<TransitLPProject>{
		
		public FetchProject(String id){
			ProjectService.Util.getInstance().readEntity(id, this);
		}
		
		@Override
		public void onFailure(Throwable caught) {
			System.out.println("Error fetching the building project");
		}

		@Override
		public void onSuccess(TransitLPProject result) {
			if(result != null){
				project = result;
				readyToLaunch();
				System.out.println("Project Found");
			}else{
				System.out.println("New Project needed");
				new CreateProject(user.getId());
			}
			
		}
		
	}
	
	private class CreateProject implements AsyncCallback<TransitLPProject>{
		
		public CreateProject(String id){
			UserService.Util.getInstance().createBuildingProjectFromTransit(id,
					new TransitLPProject(), this);
		}

		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Error while creating the building project");			
		}

		@Override
		public void onSuccess(TransitLPProject result) {
			if(result != null){
				project = result;
				System.out.println("New Project Created");
				readyToLaunch();
				
			}else{
				System.out.println("Error creating the building project (null)");
			}
			
		}
		
	}
	
	private class Login implements AsyncCallback<LoginInfo>{

		public Login(){
			LoginService.Util.getInstance().login(Window.Location.getHref(), this);
		}
		
		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Failure in the login service " + caught.getMessage());
			
		}

		@Override
		public void onSuccess(LoginInfo result) {
			if(result.isLoggedIn()) {
				  view.waitingPopup(true);
				  view.setLogOut(result);
		          new UserLogin(result.getEmailAddress());
		        } else {
		          view.showLogin(result.getLoginUrl());
		        }
			
		}
		
	}
}
