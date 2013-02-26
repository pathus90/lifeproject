package com.unilorraine.projetdevie.client.ui.viewmodules;

/**
 * Makes a app module registerable.
 * Actually it just extends it with some user displays like a name and a picture so a ui could let the user pick this module.
 * See also {@link AppModule} for more informations about app modules.
 * @author Christophe
 *
 */
public interface RegisterableModule extends AppModule {

	/**
	 * Gets the name for this module
	 * @return the name of the module
	 */
	public String getModuleName();
	
	/**
	 * Gets the picture link for this module
	 * @return returns the picture link
	 */
	public String getModulePictureLink();
}
