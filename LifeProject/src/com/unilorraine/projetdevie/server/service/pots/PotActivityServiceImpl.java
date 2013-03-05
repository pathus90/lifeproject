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

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.unilorraine.projetdevie.client.service.pots.PotActivityService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.AbstractLPPot;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.LPPotActivity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitHelper;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;
import com.unilorraine.projetdevie.server.service.AbstractPotServiceImpl;
import com.unilorraine.projetdevie.server.service.PMF;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PotActivityServiceImpl extends AbstractPotServiceImpl<TransitLPActivity, LPActivity> implements PotActivityService {

	@Override
	protected AbstractLPEntity<TransitLPPot<TransitLPActivity>> getLPEntity() {
		return new LPPotActivity();
	}

	@Override
	protected Class getLPEntityClass() {
		return LPPotActivity.class;
	}
}
