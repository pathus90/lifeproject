package com.unilorraine.projetdevie.client.ui.images.tasks;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Interface to interact with Task Images.
 * TODO This was used to simplify prototype implementation. An other system should be put in place, 
 * because this one does for example not allow runtime user content upload.
 * @author Christophe
 *
 */
public interface TaskImage extends ClientBundle {

	  @Source("play.jpg")
	  ImageResource play();

	  @Source("rules.jpg")
	  ImageResource rules();
}
