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
package com.unilorraine.projetdevie.client.service.helperinterfaces;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.AbstractLPPot;
import com.unilorraine.projetdevie.client.shared.jdoentities.pots.IPot;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPPot;

/**
 * The pot service handles basic CRUD opearations, adding entities to the pot, editing the linked entities and get TransitPot by links
 * @author Christophe
 *
 * @param <T> the transit entity used by the stored 
 */
@RemoteServiceRelativePath("PotService")
public interface IPotService<T extends ITransitEntity, S extends AbstractLPEntity<T>> extends RemoteService, ICrudService<TransitLPPot<T>> {
	
	/**
	 * Add an entity to the Pot. This method will not return a Transitpot, this would be heavy for bandwidth.
	 * @param id of the pot
	 * @return the transit object of the newly created stored entity, null if an error occurred
	 */
	public T addStoredEntitiy(String id);
	
	/**
	 * Add an entity to the Pot. This method will not return a Transitpot, this would be heavy for bandwidth.
	 * @param id of the pot
	 * @param entity the transit of an entity to add to the pot
	 * @return the transit object of the newly created stored entity, null if an error occurred
	 */
	public T addStoredEntitiy(String id, T entity);
	
	/**
	 * This methods gets all the pots link with the keys passed, see {@link IPot} for explanations about linked entities 
	 * @param link the id of the link we want the pots from
	 * @return a list of pots linked with passed ids
	 */
	public List<TransitLPPot<T>> getTransitPotsByLink(String link);
	
	/**
	 * Get all the links for a pot
	 * @param id the id of the pot
	 * @return List of the ids of the linked entities
	 */
	public List<String> getAllLinks(String id);
	
	/**
	 * Add a link to a pot
	 * @param id the id of a pot
	 * @return true if the id could be add, false if an error occurred
	 */
	public boolean addLink(String id, String link);
	
	/**
	 * Remove a link from the pot
	 * @param id the id of the pot
	 * @param link the link to be removed
	 * @return true if removed, false if an error occurred
	 */
	public boolean removeLink(String id, String link);
}
