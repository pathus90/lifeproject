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
package com.unilorraine.projetdevie.client.ui.viewmodules.apphandlermodule;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.unilorraine.projetdevie.client.ui.tilerecord.ModuleRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;

/**
 * The activity for the module manager. For more informations see {@link ModuleHandlerView}
 */
public class ModuleHandlerActivity extends AbstractAppModule implements ModuleHandlerView.Presenter {
	
	private static final String MODULE_NAME = "Modules";
	private static final String MODULE_PIC_LINK = "http://openclipart.org/image/256px/svg_to_png/116869/bee_normal.png";
	
	private List<RegisterableModule> modules;
	
	private ModuleHandlerView view;
	
	public ModuleHandlerActivity() {
		setModuleName(MODULE_NAME);
		setModulePictureLink(MODULE_PIC_LINK);
		
		modules = new ArrayList<RegisterableModule>();
	}

	@Override
	public void onStart() {
		if(view == null){
			view = new ModuleHandlerViewImpl();//GWT.create(ModuleHandlerView.class);
			view.initTileGrid(createRecords());
			view.setPresenter(this);
		}
		
		//Modules should already have been added;
		
		
		//Notify the super class that we are ready
		//You need to this or the module wont start
		super.onStart();
	}

	/**
	 * Create the records out of the {@link RegisterableModule} that are registered so they can be injected in the view
	 * @return the module records array for the {@link TileGrid} in the view 
	 */
	private ModuleRecord[] createRecords(){
		List<ModuleRecord> records = new ArrayList<ModuleRecord>();
		
		for(RegisterableModule module : modules){
			records.add(new ModuleRecord(module));
		}
		ModuleRecord[] recordsArray = new ModuleRecord[records.size()];
		
		return records.toArray(recordsArray);
	}

	@Override
	public List<RegisterableModule> getModules() {
		return modules;
	}

	@Override
	public void setModules(List<RegisterableModule> modules) {
		this.modules = modules;
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

	@Override
	public void onRecordClick(RecordClickEvent event) {
		Record record =  event.getRecord();
		Object moduleObject = record.getAttributeAsObject("module");
		
		if(moduleObject instanceof RegisterableModule){
			fireConnectModule((RegisterableModule)moduleObject);
		}
		
	}
	
}
