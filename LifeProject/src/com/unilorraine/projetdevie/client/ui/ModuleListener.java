package com.unilorraine.projetdevie.client.ui;

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
}
