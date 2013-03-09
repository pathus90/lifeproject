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
package com.unilorraine.projetdevie.client.ui.viewmodules.apphandlermodule;

import java.lang.reflect.Constructor;
import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.events.RecordClickHandler;
import com.unilorraine.projetdevie.client.activity.ApplicationPanelActivity;
import com.unilorraine.projetdevie.client.ui.tilerecord.ModuleRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;

/**
 * Interface for a module handler. The handlers job is to start the other modules.
 * Right now the Module is used in a very naive way, the object holder does just complete the list of {@link AppModule}  by means of the setter 
 * and the module handler is set as a default module. For implementation details see {@link ApplicationPanelActivity}.<br/>
 * <strong>Important</strong> In this implementation the constructors if the {@link AppModule} are only called <strong>one</strong> so if you need to reset them, 
 * you have to do it in the {@link AppModule#onStart()} or {@link AppModule#onDestroy()} methods.
 * The activity basically just has to construct the {@link ModuleRecord} list out of the module list in it's {@link AppModule#onStart()} method.
 * Then set the {@link TileGrid} that is used to show the modules and we are golden.<br/>
 * The very cool part about module handler is that it is a module it self which raises the egg or chicken question.
 */
public interface ModuleHandlerView extends IsWidget {
  
	void setName(String helloName);

	void setPresenter(Presenter listener);
	
	/**
	 * Initialize the tile grid with some module records
	 * @param records
	 */
	void initTileGrid(ModuleRecord[] records);

	/**
	 * Activity interface for the module handler "module"
	 * @author Christophe
	 *
	 */
	public interface Presenter extends AppModule, RecordClickHandler{

		/**
		 * Get the list of modules assigned to the module handler
		 * @return
		 */
		List<RegisterableModule> getModules();

		/**
		 * Set the list of modules assigned to the module handler
		 * @param modules
		 */
		void setModules(List<RegisterableModule> modules);
		
	}
}
