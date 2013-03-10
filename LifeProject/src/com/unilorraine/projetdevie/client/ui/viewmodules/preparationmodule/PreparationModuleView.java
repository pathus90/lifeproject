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
package com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule;

import java.util.List;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;
import com.smartgwt.client.data.Record;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.MenuModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule.guiobjects.UnitPanel;
import com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule.guiobjects.UnitTileGrid;
import com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule.guiobjects.UnitTileGridListener;

/**
 * View base interface.
 * Extends IsWidget so a view impl can easily provide its container widget.
 */
public interface PreparationModuleView extends IsWidget {

	void setPresenter(Presenter listener);
	
	void setText(String text);
	
	void setProjectActivities(ActivityRecord[] activities);
	
	List<Record> getProjectActivities();
	
	void setSchemaActivities(ActivityRecord[] activities);
	
	void addExistingUnitChoice(String id, ActivityRecord[] activities);
	
	List<List<Record>> getUnitChoices();
	
	void addNewUnitChoice();
	
	void removeGrid(UnitPanel unitPanel);
	
	void setCategory(String category);

	public interface Presenter extends MenuModule, RegisterableModule, UnitTileGridListener {
		void setCategory(TransitLPCategory transitCategory);
		TransitLPCategory getCategory();
		
		void saveProject();
	}
}
