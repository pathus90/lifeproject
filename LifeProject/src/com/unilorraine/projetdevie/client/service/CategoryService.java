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
package com.unilorraine.projetdevie.client.service;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unilorraine.projetdevie.client.service.helperinterfaces.ICrudService;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;

@RemoteServiceRelativePath("CategoryService")
public interface CategoryService extends RemoteService, ICrudService<TransitLPCategory> {
	
	/**
	 * Get the transit object for each categories 
	 * @return a list of all the transit objects for categories
	 */
	public ArrayList<TransitLPCategory> getAllCategories();
	
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static CategoryServiceAsync instance;
		public static CategoryServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(CategoryService.class);
			}
			return instance;
		}
	}
}
