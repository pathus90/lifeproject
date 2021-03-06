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
package com.unilorraine.projetdevie.client.service.pots;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unilorraine.projetdevie.client.service.helperinterfaces.IPotService;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.IPot;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;

@RemoteServiceRelativePath("PotActivityService")
public interface PotActivityService extends IPotService<TransitLPActivity, LPActivity> {
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static PotActivityServiceAsync instance;
		public static PotActivityServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(PotActivityService.class);
			}
			return instance;
		}
	}
}
