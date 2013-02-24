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

import com.unilorraine.projetdevie.client.service.pots.PotUserGroupService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.accountentities.LPUserGroup;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.LPPotUserGroup;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUserGroup;
import com.unilorraine.projetdevie.server.service.AbstractPotServiceImpl;

public class PotUserGroupServiceImpl extends AbstractPotServiceImpl<TransitLPUserGroup, LPUserGroup> implements PotUserGroupService{

	@Override
	protected AbstractLPEntity<TransitLPPot<TransitLPUserGroup>> getLPEntity() {
		return new LPPotUserGroup();
	}

	@Override
	protected Class getLPEntityClass() {
		return LPPotUserGroup.class;
	}
}
