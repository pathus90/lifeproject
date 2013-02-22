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
package com.unilorraine.projetdevie.server.service;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.unilorraine.projetdevie.client.service.CategoryService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPCategory;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CategoryServiceImpl extends CRUDRemoteService<TransitLPCategory> implements CategoryService {

	@Override
	public ArrayList<TransitLPCategory> getAllCategories() {
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		ArrayList<TransitLPCategory> list = new ArrayList<TransitLPCategory>();
		
		try{
			
			Query q = pm.newQuery(LPCategory.class);
			List<LPCategory> listCategory = (List<LPCategory>) q.execute();
			for(LPCategory category : listCategory){
				list.add(category.createTransit());
			}
			
		}catch(JDOObjectNotFoundException notFound) {
			return null;
		}finally{
			pm.close();
		}
		
		return list;
	}

	@Override
	protected AbstractLPEntity<TransitLPCategory> getLPEntity() {
		return new LPCategory();
	}

	@Override
	protected Class getLPEntityClass() {
		return LPCategory.class;
	}

}
