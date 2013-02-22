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

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.smartgwt.client.widgets.tile.TileRecord;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;

/**
 * View base interface.
 * Extends IsWidget so a view impl can easily provide its container widget.
 */
public interface CategoryView extends IsWidget {
  
	void setName(String helloName);

	void setPresenter(Presenter listener);
	
	/**
	 * TODO to comment
	 * @param arrayCatReccord
	 */
	void initTileGrid(CategoryRecord[] arrayCatReccord);

	public interface Presenter {
		/**
		 * Navigate to a new Place in the browser.
		 */
		void goTo(Place place);

		/**
		 * Load all categories from server
		 * @return
		 */
		void setCategories();
		
	}
}
