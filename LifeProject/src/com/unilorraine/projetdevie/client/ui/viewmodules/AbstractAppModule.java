package com.unilorraine.projetdevie.client.ui.viewmodules;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.ModuleListener;

/**
 * Abstract implementation of an {@link AppModule} extended by {@link RegisterableModule}. It handles the {@link ModuleListener} structure, and the {@link AppContext}.
 * It adds a fireDestroyReady and fireDisplayReady methods to be used to notify the {@link ModuleListener}.
 * It adds protected setters for the name and the picture link so the implementations instantiate them but a default value is given in the default constructor.
 * @author Christophe
 *
 */
public abstract class AbstractAppModule implements RegisterableModule {

	private static final String DEFAULT_NAME = "App Module";
	private static final String DEFAULT_LINK = "http://openclipart.org/image/128px/svg_to_png/67/Andy_Tools_Hammer_Spanner.png";
	
	/**
	 * The app context
	 */
	private AppContext context;
	
	/**
	 * The name of this module
	 */
	private String moduleName;
	
	/**
	 * the picture link for this module
	 */
	private String modulePictureLink;
	
	/**
	 * Teh module listener for this module
	 */
	private ModuleListener listener;
	
	
	/**
	 * Default constructor, sets default for name and picture link
	 */
	public AbstractAppModule() {
		moduleName = DEFAULT_NAME;
		modulePictureLink = DEFAULT_LINK;
	}

	
	
	
	@Override
	public void onStart() {
		fireDisplayReady();
	}




	@Override
	public void onDestroy() {
		fireDestructionReady();
	}




	@Override
	public String getModuleName() {
		return moduleName;
	}

	@Override
	public String getModulePictureLink() {
		return modulePictureLink;
	}

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
	protected void fireDestructionReady(){
		if(listener != null)
			listener.moduleDestructionReady();
	}
	
	/**
	 * Sets the name for this module
	 * @param moduleName the new name of this module
	 */
	protected void setModuleName(String moduleName){
		this.moduleName = moduleName;
	}
	
	/**
	 * Sets the picture link for this module
	 * @param moduleName the new picture link of this module
	 */
	protected void setModulePictureLink(String modulePictureLink){
		this.modulePictureLink = modulePictureLink;
	}
	
	

}
