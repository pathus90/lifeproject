package com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects;

/**
 * To be passed to the {@link UnitTileGrid}
 * @author Christophe
 *
 */
public interface UnitTileGridListener {

	/**
	 * The UnitTileGrid can fire this one up when the old new grid is filled with some activities
	 */
	public void needNewGrid();
	
	/**
	 * Delete the passed unitPanel
	 * @param unitPanel the unitPanel to be deleted
	 */
	public void deleteGrid(UnitPanel unitPanel);
}
