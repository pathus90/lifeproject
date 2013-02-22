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
package com.unilorraine.projetdevie.client.activity;

import java.util.ArrayList;
import java.util.Iterator;

import com.smartgwt.client.widgets.tile.TileRecord;
import com.unilorraine.projetdevie.client.ClientFactory;
import com.unilorraine.projetdevie.client.place.CategoryPlace;
import com.unilorraine.projetdevie.client.service.ActivityServiceAsync;
import com.unilorraine.projetdevie.client.service.CategoryService;
import com.unilorraine.projetdevie.client.service.CategoryServiceAsync;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.ui.CategoryView;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;
import com.unilorraine.projetdevie.server.service.CategoryServiceImpl;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.shared.GWT;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class CategoryActivity extends AbstractActivity implements CategoryView.Presenter {
	/**
	 * Used to obtain views, eventBus, placeController.
	 * Alternatively, could be injected via GIN.
	 */
	private ClientFactory clientFactory;

	/**
	 * Sample property.
	 */
	private String name;
	private TileRecord[] tiles;
	private CategoryService categoryService;

	private CategoryServiceAsync service = GWT.create(CategoryService.class);
	
	public CategoryActivity(CategoryPlace place, ClientFactory clientFactory) {
		this.name = place.getName();
		this.clientFactory = clientFactory;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		CategoryView view = clientFactory.getCategoryView();
		System.out.println("View " + view);
		view.setName(name);
		view.setPresenter(this);
		
		setCategories();
		
		containerWidget.setWidget(view.asWidget());
	}

	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}

	/**
	 * @see CategoryView.Presenter#goTo(Place)
	 */
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}

	@Override
	public void setCategories() {


	    if ( service == null) {
	      service = GWT.create(CategoryService.class);
	    }
		
		AsyncCallback<ArrayList<TransitLPCategory>> callback = new AsyncCallback<ArrayList<TransitLPCategory>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println(caught.getMessage());
				
			}

			@Override
			public void onSuccess(ArrayList<TransitLPCategory> result) {
				if (result == null)
					System.out.println("Error server!");
				else{
//					categories = categoryService.getAllCategories();
					CategoryRecord[] arrayCatReccord = new CategoryRecord[result.size()];
					int i = 0;
					for(TransitLPCategory cat : result){
						arrayCatReccord[i] = new CategoryRecord(cat);
						i++;
					}
					clientFactory.getCategoryView().initTileGrid(arrayCatReccord);	
				}
			}
			
		};
		service.getAllCategories(callback);
		
	}
}
