package com.unilorraine.projetdevie.client.ui;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
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
	 * Redraws the panel in the module listener
	 * @param panel the panel to be redrawn
	 */
	void redraw(Panel panel);
	
	/**
	 * @param item
	 * @param fireEvents
	 * @see com.google.gwt.user.client.ui.Tree#setSelectedItem(com.google.gwt.user.client.ui.TreeItem, boolean)
	 */
	void setSelectedItem(TreeItem item, boolean fireEvents);
	
	
}
