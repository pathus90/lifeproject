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

import com.unilorraine.projetdevie.client.service.TaskService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPTask;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;

public class TaskServiceImpl extends CRUDRemoteService<TransitLPTask> implements TaskService {

	@Override
	protected AbstractLPEntity<TransitLPTask> getLPEntity() {
		return new LPTask();
	}

	@Override
	public TransitLPTask deleteEntity(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransitLPTask readEntity(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
