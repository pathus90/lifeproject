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

import javax.jdo.PersistenceManager;

import com.unilorraine.projetdevie.client.service.ActivityService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPTask;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;

/**
 * Implementation du service rpc concernant les pojos LPActivity. Toutes les fonctions nécéssaires à la communication avec le serveur se trouve ici.
 * @author Christophe
 *
 */
public class ActivityServiceImpl extends CRUDRemoteService<TransitLPActivity> implements ActivityService {

	@Override
	protected AbstractLPEntity<TransitLPActivity> getLPEntity() {
		return new LPActivity();
	}
	
	@Override
	protected Class<LPActivity> getLPEntityClass() {
		return LPActivity.class;
	}

	@Override
	public ArrayList<TransitLPTask> getAllTasks(String id) {
		ArrayList<TransitLPTask> taskList = new ArrayList<TransitLPTask>();
		LPActivity activity;
		PersistenceManager pm = PMF.get().getPersistenceManager(); 
		
		try{
			activity = pm.getObjectById(LPActivity.class, id);
			for(LPTask task : activity.getTasks()){
				taskList.add(task.createTransit());
			}
			
		}finally{
			pm.close();
			
		}
		
		return taskList;
	}

	public TransitLPTask addTask(String id) {
		return addTask(id, null);
	}

	
	public TransitLPTask addTask(String id, TransitLPTask transitTask) {
		LPActivity activity;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		LPTask task = new LPTask();
		
		try{
			activity = pm.getObjectById(LPActivity.class, id);
			
			if(transitTask != null)
				task.updateFromTransit(transitTask);
			
			activity.addTask(task);
			pm.makePersistent(activity);
			
			
		}finally{
			pm.close();
			
		}
		
		return task.createTransit();
	}
}
