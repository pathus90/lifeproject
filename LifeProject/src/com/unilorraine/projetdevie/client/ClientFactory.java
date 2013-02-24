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

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.unilorraine.projetdevie.client.ui.TaskAdminView;
import com.unilorraine.projetdevie.client.ui.ActorHolderView;
import com.unilorraine.projetdevie.client.ui.CategoryView;
import com.unilorraine.projetdevie.client.ui.CategorySelectionView;

/**
 * ClientFactory helpful to use a factory or dependency injection framework like GIN to obtain 
 * references to objects needed throughout your application like the {@link EventBus},
 * {@link PlaceController} and views.
 */
public interface ClientFactory {

	EventBus getEventBus();

	PlaceController getPlaceController();
	public SampleView getSampleView();
	public TaskAdminView getTaskAdminView();
	public ActorHolderView getActorHolderView();
	public CategoryView getCategoryView();
	public CategorySelectionView getCategorySelectionView();
}
