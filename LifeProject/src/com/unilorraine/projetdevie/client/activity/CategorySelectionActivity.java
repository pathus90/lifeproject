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
import java.util.List;

import com.unilorraine.projetdevie.client.ClientFactory;
import com.unilorraine.projetdevie.client.place.CategorySelectionPlace;
import com.unilorraine.projetdevie.client.service.pots.PotActivityService;
import com.unilorraine.projetdevie.client.service.pots.PotCategoryService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.client.ui.CategorySelectionView;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class CategorySelectionActivity extends AbstractActivity implements CategorySelectionView.Presenter {
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

	public CategorySelectionActivity(CategorySelectionPlace place, ClientFactory clientFactory) {
		this.name = place.getName();
		this.clientFactory = clientFactory;
		
		records = place.getRecords();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		CategorySelectionView view = clientFactory.getCategorySelectionView();
		view.setName(name);
		view.setPresenter(this);
		
		view.initTileGrid(records);
		
		containerWidget.setWidget(view.asWidget());
		
	}

	/*
	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}
	*/

	/**
	 * @see CategorySelectionView.Presenter#goTo(Place)
	 */
	public void goTo(Place place) {
		clientFactory.getPlaceController().goTo(place);
	}
}
