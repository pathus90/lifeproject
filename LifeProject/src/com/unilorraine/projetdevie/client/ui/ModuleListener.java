package com.unilorraine.projetdevie.client.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;

/**
 * Listener interface for controllers enclosing modules, so they can let them prepare them self for displaying or destruction (for example fetching or saving operations).
 * @author Christophe
 *
 */
public interface ModuleListener {

	/**
	 * The module is ready to be displayed
	 */
	public void moduleDisplayReady();
	
	/**
	 * The module is ready to be destroyed
	 */
	public void moduleDestructionReady();
	
	/**
	 * Connect a module to the application panel. 
	 * This should connect the menu, the plugin panel to this module and set the {@link AppContext}
	 * This method is in the listener so a module can call an other module to replace it self
	 * @param module the module to be connected
	 */
	void connectModule(AppModule module);
	
	/**
	 * Draws the passed widget in the module listener. Will logically <strong>not</strong> use the {@link AppModule#getWidget()} form the app module. 
	 * Use this if you you have fix starting view for example.
	 * @param widget the widget to be redrawn
	 */
	void redraw(Widget widget);
	
	/**
	 * Redraws the panel in the module listener by calling the {@link AppModule#getWidget()}
	 */
	void redraw();
	
	/**
	 * @param item
	 * @param fireEvents
	 * @see com.google.gwt.user.client.ui.Tree#setSelectedItem(com.google.gwt.user.client.ui.TreeItem, boolean)
	 */
	void setSelectedItem(TreeItem item, boolean fireEvents);
	
	
}
