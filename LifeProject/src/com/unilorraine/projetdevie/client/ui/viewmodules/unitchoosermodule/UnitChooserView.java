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
package com.unilorraine.projetdevie.client.ui.viewmodules.unitchoosermodule;

import com.google.gwt.user.client.ui.IsWidget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.smartgwt.client.widgets.tile.events.RecordDoubleClickHandler;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;

/**
 * View base interface.
 * Extends IsWidget so a view impl can easily provide its container widget.
 */
public interface UnitChooserView extends IsWidget {
  

	void setPresenter(Presenter listener);

	void setUnitContainer(Record[] units);
	
	void setActivities(Record[] activities);
	
	void addActivity(Record activity);
	
	Record getTargetRecord();
	
	void showNoMoreUnitsDialog();
	
	void showLoading();
	
	void hideLoading();
	
	void resest();
	
	public interface Presenter extends RegisterableModule, RecordDoubleClickHandler {
		
	}
}
