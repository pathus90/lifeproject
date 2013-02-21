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
package com.unilorraine.projetdevie.client.mvp;

import com.unilorraine.projetdevie.client.ClientFactory;
import com.unilorraine.projetdevie.client.activity.ActorHolderActivity;
import com.unilorraine.projetdevie.client.activity.SampleActivity;
import com.unilorraine.projetdevie.client.activity.TaskAdminActivity;
import com.unilorraine.projetdevie.client.place.ActorHolderPlace;
import com.unilorraine.projetdevie.client.place.SamplePlace;
import com.unilorraine.projetdevie.client.place.TaskAdminPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

/**
 * ActivityMapper associates each {@link Place} with its corresponding {@link Activity}.
 */
public class AppActivityMapper implements ActivityMapper {

	/**
	 * Provided for {@link Activitie}s.
	 */
	private ClientFactory clientFactory;

	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
	  
		if (place instanceof SamplePlace)
			return new SampleActivity((SamplePlace) place, clientFactory);
		if (place instanceof TaskAdminPlace)
			return new TaskAdminActivity((TaskAdminPlace) place, clientFactory);
		if (place instanceof ActorHolderPlace)
			return new ActorHolderActivity((ActorHolderPlace)place, clientFactory);
		return null;
	}

}
