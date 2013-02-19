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
package com.unilorraine.projetdevie.client;

import com.unilorraine.projetdevie.client.ui.SampleView;
import com.unilorraine.projetdevie.client.ui.SampleViewImpl;
import com.unilorraine.projetdevie.client.ui.TaskAdminView;
import com.unilorraine.projetdevie.client.ui.TaskAdminViewImpl;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

/**
 * Sample implementation of {@link ClientFactory}.
 */
public class ClientFactoryImpl implements ClientFactory {
  
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);
	private static final SampleView view = new SampleViewImpl();
	private static final TaskAdminView taskAdminView = new TaskAdminViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public SampleView getSampleView() {
		return view;
	}

	@Override
	public TaskAdminView getTaskAdminView() {
		return taskAdminView;
	}
}
