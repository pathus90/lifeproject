package com.unilorraine.projetdevie.client.ui.viewmodules;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.ApplicationPanelView;
import com.unilorraine.projetdevie.client.ui.ModuleListener;

/**
 * Interface that represents an module for the application. A app module is a self sufficient MVC based client side application, that uses the server.
 * The AppModules are indented to be displayed with the {@link ApplicationPanelView}, which handles the menu style and the main module panel.
 * But nothing forces you to work like this because AppModules are nothing more then a controller and a view. You could even combine them together.
 * We decided not to use the {@link com.google.gwt.user.client.ui.Composite} class but a simple {@link Panel} for convenience reason. 
 * Please let us know if you disagree with that choice.
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
