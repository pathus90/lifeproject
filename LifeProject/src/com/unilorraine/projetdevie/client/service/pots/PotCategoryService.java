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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unilorraine.projetdevie.client.service.helperinterfaces.IPotService;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;

@RemoteServiceRelativePath("PotCategoryService")
public interface PotCategoryService extends IPotService<TransitLPCategory, LPCategory> {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static PotCategoryServiceAsync instance;
		public static PotCategoryServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(PotCategoryService.class);
			}
			return instance;
		}
	}
}
