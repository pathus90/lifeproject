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
package com.unilorraine.projetdevie.server.service.pots;

import com.unilorraine.projetdevie.client.service.pots.PotCategoryService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.LPPotCategory;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.server.service.AbstractPotServiceImpl;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PotCategoryServiceImpl extends AbstractPotServiceImpl<TransitLPCategory, LPCategory> implements PotCategoryService {

	@Override
	protected AbstractLPEntity<TransitLPPot<TransitLPCategory>> getLPEntity() {
		return new LPPotCategory();
	}

	@Override
	protected Class getLPEntityClass() {
		return LPPotCategory.class;
	}
}
