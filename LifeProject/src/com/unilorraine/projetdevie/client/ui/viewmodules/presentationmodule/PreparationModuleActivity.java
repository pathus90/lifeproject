/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppMenuModule;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class PreparationModuleActivity extends AbstractAppMenuModule implements PreparationModuleView.Presenter {

	//TODO view information shouldn't be set here
	private static final String MODULE_NAME = "Preparation du LP";
	private static final String MODULE_PIC_LINK = "";
	
	private static final String MENU_ONE = "Menu 1";
	private static final String SUBMENU_ONE_ONE = "Submenu 1";
	private static final String SUBMENU_ONE_TWO = "Submenu 2";
	private static final String SUBMENU_ONE_TWO_ONE = "Subsubmenu 1";
	private static final String MENU_TWO = "Menu 2";
	private static final String SUBMENU_TWO = "Submenu 1";
	private static final String MENU_THREE = "Menu 3";
	
	
	private static PreparationModuleView view;
	
	
	//Note that we don't actually create anything in the constructor. 
	//By doing so we embrace the possibiliy of using async fetching without white screen delay for the user.
	public PreparationModuleActivity() {
		super();
		setModuleName(MODULE_NAME);
	}

	@Override
	public void onStart() {
		if(view ==null)
			view = new PreparationModuleViewImpl();//(PreparationModuleView)GWT.create(PreparationModuleView.class);
		
		//The menu creation
		intiMenu();
		
		//This is very important, if you don't fire up the listener your module won't be shown
		super.onStart();
	};
	
	/**
	 * Initialize the menu
	 */
	private void intiMenu() {
		
		removeAll();
		
		//Item 1 sub-tree
		TreeItem item1 = new TreeItem();
		
		item1.setText(MENU_ONE);
		item1.setUserObject(MENU_ONE);
		
		TreeItem subitem1 = new TreeItem(); 
		subitem1.setText(SUBMENU_ONE_ONE);
		subitem1.setUserObject(SUBMENU_ONE_ONE);
		item1.addItem(subitem1);
		
		TreeItem subitem2 = new TreeItem(); 
		subitem2.setText(SUBMENU_ONE_TWO);
		subitem2.setUserObject(SUBMENU_ONE_TWO);
		item1.addItem(subitem2);
		
		TreeItem subsubitem = new TreeItem(); 
		subsubitem.setText(SUBMENU_ONE_TWO_ONE);
		subsubitem.setUserObject(SUBMENU_ONE_TWO_ONE);
		subitem2.addItem(subsubitem);
		
		

		//Item 2 sub-tree
		TreeItem item2 = new TreeItem();
		item2.setText(MENU_TWO);
		item2.setUserObject(MENU_TWO);
		
		TreeItem subitem3 = new TreeItem(); 
		subitem3.setText(SUBMENU_TWO);
		subitem3.setUserObject(SUBMENU_TWO);
		item2.addItem(subitem3);
		
		TreeItem item3 = new TreeItem();
		item3.setText(MENU_THREE);
		item3.setUserObject(MENU_THREE);
		
		addItem(item1);
		addItem(item2);
		addItem(item3);
		
		
	}

	//TODO to implement
	@Override
	public void handleMenuAction(SelectionEvent<TreeItem> event) {
		System.out.println("Some event occured on " + event.getSelectedItem().getText());
	}

	@Override
	public Widget getWidget() {
		if(view != null)
			return view.asWidget();
		else{
			System.err.println("The PreparationModuleView is not instantiated!");
			return null;
		} 
	}
	
}
