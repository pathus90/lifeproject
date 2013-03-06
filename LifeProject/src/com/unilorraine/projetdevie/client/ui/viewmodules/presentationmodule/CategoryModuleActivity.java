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


import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.tile.events.RecordClickEvent;
import com.unilorraine.projetdevie.client.ClientFactory;
import com.unilorraine.projetdevie.client.place.CategorySelectionPlace;
import com.unilorraine.projetdevie.client.service.pots.PotCategoryService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPInstitution;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.client.ui.CategorySelectionView;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;
import com.unilorraine.projetdevie.client.ui.tilerecord.ModuleRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppMenuModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class CategoryModuleActivity extends AbstractAppModule implements CategoryModuleView.Presenter {

	public final static String MODULE_NAME = "Préparation Projet de Vie";
	
	private CategoryModuleView view = null;
	/**
	 * Used to obtain views, eventBus, placeController.
	 * Alternatively, could be injected via GIN.
	 */
	private ClientFactory clientFactory;

	/**
	 * Sample property.
	 */
	private String name;
	
	/**
	 * The id the categories should be fetched from
	 */
	private CategoryRecord[] records;
	
	private String institutionID;

	public CategoryModuleActivity() {
		setModuleName(MODULE_NAME);
		
	}
	
	@Override
	public void onStart() {
		
		institutionID = getAppContext().getContextLinks().get(0);
		getCategoriesAsync(institutionID);
		
		view = new CategoryModuleViewImpl();
		
	};

	@Override
	public Widget getWidget() {
		if(view !=null)
			return view.asWidget();
		else
			return null;
	}
	
	private final void setView(CategoryModuleView view){
		this.view = view;
		this.view.setPresenter(this);
	}
	
	/**
	 * Get the categories
	 */
	private void getCategoriesAsync(String institutionID){
		
		
		AsyncCallback<List<TransitLPPot<TransitLPCategory>>> callback = new AsyncCallback<List<TransitLPPot<TransitLPCategory>>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<TransitLPPot<TransitLPCategory>> result) {
				if(result != null){
					
					CategoryRecord[] records;
					List<CategoryRecord> listRecords = new ArrayList<CategoryRecord>();
					for(TransitLPPot<TransitLPCategory> pot : result){
						for(TransitLPCategory category : pot.getStoredEntities()){
							listRecords.add(new CategoryRecord(category));
						}
					}
					records = new CategoryRecord[listRecords.size()];
					records = listRecords.toArray(records);
					
					view.initTileGrid(records);
					setView(view);
					fireDisplayReady();
				}
				
			}
		};
		
		PotCategoryService.Util.getInstance().getTransitPotsByLink(institutionID, callback );
	}

	@Override
	public void onRecordClick(RecordClickEvent event) {
		Record record =  event.getRecord();
		if(record instanceof CategoryRecord){
			TransitLPCategory category = ((CategoryRecord)record).getCategoryTransit();
			PreparationModuleActivity presentation = new PreparationModuleActivity();
			presentation.setCategory(category);
			fireConnectModule(presentation);
		}
		
	}

}
