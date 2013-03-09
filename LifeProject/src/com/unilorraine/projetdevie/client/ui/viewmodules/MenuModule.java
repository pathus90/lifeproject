package com.unilorraine.projetdevie.client.ui.viewmodules;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * A Menu capable menu. Needs to provide {@link TreeItem} to the app container and to handle the action pushed at it.
 * See also {@link AppModule} for more informations about app modules.
 * @author Christophe
 *
 */
public interface MenuModule extends AppModule{
	
	/**
	 * Method called when a {@link TreeItem} was selected.
	 * @param event dispatched by the {@link Tree} on selection
	 */
	public void handleMenuAction(SelectionEvent<TreeItem> event);
	
	/**
	 * Method called to create the menu in the module holder. You need to use the {@link TreeItem#setUserObject(Object)} method so you will be able 
	 * to handle the selection event passed by {@link AppModule#handleMenuAction(Object)} 
	 * @return list of the TreeItem you wish to display as menu
	 */
	public List<TreeItem> getMenuItems();

}
