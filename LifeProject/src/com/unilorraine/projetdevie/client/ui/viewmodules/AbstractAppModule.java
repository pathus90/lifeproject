package com.unilorraine.projetdevie.client.ui.viewmodules;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.ModuleListener;

/**
 * Abstract implementation of an {@link AppModule}. It handles the {@link ModuleListener} structure, and the {@link AppContext}.
 * It adds a fireDestroyReady and fireDisplayReady methods to be used to notify the {@link ModuleListener}.
 * The abstract overrides the onStart ond onDestroy methods to juste fire the listener, meaning that if you want to fetch something before displaying for 
 * example you have to explicitly override the onStart method in your implementation.
 * @author Christophe
 *
 */
public abstract class AbstractAppModule implements AppModule {

	/**
	 * The app context
	 */
	private AppContext context;
	
	/**
	 * 
	 */
	private ModuleListener listener;
	
	@Override
	public void setAppContext(AppContext context) {
		this.context = context;

	}

	@Override
	public AppContext getAppContext() {
		return context;
	}

	@Override
	public void setModuleListener(ModuleListener listener) {
		this.listener = listener;

	}

	@Override
	public ModuleListener getModuleListener() {
		return listener;
	}
	
	@Override
	public void onStart() {
		fireDisplayReady();
	}

	@Override
	public void onDestroy() {
		fireDescrutionReady();
	}
	
	/**
	 * Simple method that fires the display ready on the listener
	 */
	protected void fireDisplayReady(){
		if(listener != null)
			listener.moduleDisplayReady();
	}
	
	/**
	 * simple method that fires the destroy ready on the listener
	 */
	protected void fireDescrutionReady(){
		if(listener != null)
			listener.moduleDestructionReady();
	}

}
