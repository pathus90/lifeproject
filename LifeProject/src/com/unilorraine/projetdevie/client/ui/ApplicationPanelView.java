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
package com.unilorraine.projetdevie.client.ui;

import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;

/**
 * View base interface.
 * Extends IsWidget so a view impl can easily provide its container widget.
 */
public interface ApplicationPanelView extends IsWidget {
	
	void setName(String helloName);

	void setPresenter(Presenter listener);
	
	/**
	 * Set the main widget of a module in the app view panel
	 * @param view the view to be set
	 */
	void setAppModuleView(Widget widget);
	
	/**
	 * set the tree items for the menu of the app module
	 * @param treeItems the list of the {@link TreeItem} for the menu
	 */
	void setAppMenuItems(List<TreeItem> treeItems);

	/**
	 * Empties the app view of all the rest of the old active module 
	 */
	void emptyModule();
	
	/**
	 * Sets the listener of the Tree
	 */
	void setMenuTreeListener( SelectionHandler<TreeItem> listener);
	
	/**
	 * Controller for ApplicationPanelView
	 * @author Christophe
	 *
	 */
	public interface Presenter extends SelectionHandler<TreeItem>, ModuleListener {
		/**
		 * Navigate to a new Place in the browser.
		 */
		void goTo(Place place);
		
		/**
		 * Getter for the app context
		 */
		AppContext getAppContext();
		
		/**
		 * Setter for the AppContext
		 * @param context the app context
		 */
		void setAppContext(AppContext context);
		
		/**
		 * Empties the app handler of all the rest of the old active module 
		 */
		void emptyModule();
		
		/**
		 * Got to the default module, for exemple the module
		 */
		void gotoDefaultMenu();
		
		/**
		 * Methode in which the {@link AppModule} activities have to be defined to be pushed in the handler  
		 * TODO This is juste a fast implementation, it really needs to be polishing 
		 */
		abstract List<RegisterableModule> moduleReferences();
		
	}
}
