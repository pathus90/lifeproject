package com.unilorraine.projetdevie.client.ui.viewmodules;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.ApplicationPanelView;
import com.unilorraine.projetdevie.client.ui.ModuleListener;
import com.unilorraine.projetdevie.client.ui.viewmodules.apphandlermodule.ModuleHandlerActivity;
import com.unilorraine.projetdevie.client.ui.viewmodules.apphandlermodule.ModuleHandlerView;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.PreparationModuleActivity;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.PreparationModuleView;

/**
 * Interface that represents an module for the application. A app module is a self sufficient MVC based client side application<br/>
 * <p><strong>Important</strong> When you implement this interface, you have to pay attention to both {@link AppModule#onStart()} and {@link AppModule#onDestroy()}. They have to fire the events form 
 * {@link ModuleListener#moduleDisplayReady()} and its counterpart {@link ModuleListener#moduleDestructionReady()} if you wish your module to be started or free the space for an an other on.<br/>
 * If you feel unsure about this extend the {@link AbstractAppModule} which will perform this action for you. <strong>It is not intended to create the view in the constructor</strong> because if you wish to create a 
 * {@link RegisterableModule} the view of all the accessible modules would be created at one and drag around. This would be a complete wast of space and time. So create your view in the {@link AppModule#onStart()} method<br/>
 * For an implementation example take a look at the {@link ModuleHandlerActivity}</p>
 * <p>The {@link AppModule} is extended by two interfaces to add functionalities. The {@link RegisterableModule} interface make the module usable by the {@link ModuleHandlerView} module.<br/>
 * The {@link MenuModule} gives the module the possibly to use the menu space of {@link ApplicationPanelView} in which it will be displayed. So you don't have to worry about menu designing what so ever. 
 * Just handle the events thrown by your listener. The {@link PreparationModuleActivity} would be an implematention example.</p>
 * <p>The AppModules are indented to be displayed with the {@link ApplicationPanelView}, which handles the menu style and the main module panel.
 * But nothing forces you to work like this because AppModules are nothing more then a controller and a view. You could even combine them together.
 * We decided not to use the {@link com.google.gwt.user.client.ui.Composite} class but a simple {@link Panel} for convenience reason.
 * Please let us know if you disagree with that choice.</p>
 * 
 * @author Christophe
 *
 */
public interface AppModule {

	/**
	 * Get the main Panel for this module. Everything should be displayed in the panel. 
	 * You completely free on what you put in it, just check out the size of the panel intending to receive your view in the implementation of {@link ApplicationPanelView}
	 * @return
	 */
	public Widget getWidget();
	
	/**
	 * Setter the context for this module. See {@link AppContext} doc for more infos.
	 * @param context the context passed on by the {@link ApplicationPanelView}
	 */
	public void setAppContext(AppContext context);
	
	/**
	 * Setter the context for this module. See {@link AppContext} doc for more infos.
	 * @return
	 */
	public AppContext getAppContext();
	
	/**
	 * Will be called when a module is to be started so it can for example fetch some informations before its displaying.
	 * After it is done you have to call the on {@link ModuleListener#moduleDisplayReady()} or this module wont be displayed.
	 * This task has to left to the implementation because it could well be that the the module needs to fetch DB entities asynchronously for example.
	 */
	public void onStart();
	
	/**
	 * Will be called when a module is to be destroyed so it can for example save some informations before its destruction, do this here.
	 * After it is done you have to call the on {@link ModuleListener#moduleDestructionReady()} or this module wont be displayed.
	 * This task has to left to the implementation because it could well be that the the module needs to fetch DB entities asynchronously for example.
	 */
	public void onDestroy();
	
	/**
	 * Setter for the module listener for this module. The module listener needs to be called for displaying or destroying the module.
	 * If you don't implement the listener structure this module will not be displayed.
	 * @param listener the listener for this module;
	 */
	public void setModuleListener(ModuleListener listener);
	
	/**
	 * Getter for the module listener for this module. The module listener needs to be called for displaying or destroying the module
	 * If you don't implement the listener structure this module will not be displayed.
	 * @return the module listener for this module
	 */
	public ModuleListener getModuleListener();
	
}
