package com.unilorraine.projetdevie.client.ui.viewmodules;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.unilorraine.projetdevie.client.ui.AppContext;
import com.unilorraine.projetdevie.client.ui.ModuleListener;

/**
 * Extension of the {@link AbstractAppModule} with a {@link TreeItem} List to work with and return it through the {@link AbstractAppModule#getMenuItems()} method. 
 * Use the existing add and remove method from this class to add some menu items.<br/>
 * See also {@link AppModule} for more informations about app modules.
 * @author Christophe
 *
 */
public abstract class AbstractAppMenuModule extends AbstractAppModule implements MenuModule {

	/**
	 * tree item list for the menu
	 */
	private List<TreeItem> itemTreeList = new ArrayList<TreeItem>();
	
	
	@Override
	public List<TreeItem> getMenuItems() {
		return itemTreeList;
	}


	public void addItem(int index, TreeItem element) {
		itemTreeList.add(index, element);
	}


	public boolean addItem(TreeItem e) {
		return itemTreeList.add(e);
	}


	public int indexOfItem(Object o) {
		return itemTreeList.indexOf(o);
	}


	public Iterator<TreeItem> itemIterator() {
		return itemTreeList.iterator();
	}


	public TreeItem removeItem(int index) {
		return itemTreeList.remove(index);
	}


	public int itemSize() {
		return itemTreeList.size();
	}

	
	public void removeAll(){
		itemTreeList = new ArrayList<TreeItem>();
	}

	public List<TreeItem> getItemTreeList() {
		return itemTreeList;
	}


	public void setItemTreeList(List<TreeItem> itemTreeList) {
		this.itemTreeList = itemTreeList;
	}

	

}
