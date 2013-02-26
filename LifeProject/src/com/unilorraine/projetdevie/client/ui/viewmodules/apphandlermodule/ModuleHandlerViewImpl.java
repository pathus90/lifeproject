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

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.unilorraine.projetdevie.client.ui.tilerecord.ModuleRecord;

/**
 * The view for the module handler. Basically it is just a {@link TileGrid}. For more informations see {@link ModuleHandlerView}
 */
public class ModuleHandlerViewImpl extends FlowPanel implements ModuleHandlerView {
	private Presenter listener;
	private Label title;
	private final TileGrid moduleGrid;
	private DetailViewerField modulePicture;
	private DetailViewerField moduleName;

	public ModuleHandlerViewImpl() {
		setSize("600px", "500px");
		
		 VStack vStack = new VStack(20);  
	     vStack.setWidth100(); 
		
		title = new Label("Module \u00E0 disposition");
		title.setStyleName("title");
		add(title);
		
		moduleGrid = new TileGrid();
		moduleGrid.setSize("501px", "100%");
		
		moduleGrid.setEdgeSize(0);
		moduleGrid.setShowEdges(false);
		
		moduleGrid.setHeight("295px");
		moduleGrid.setTileWidth(120);
		moduleGrid.setTileHeight(120);
		moduleGrid.setSelectionType(SelectionStyle.SINGLE);  
		moduleGrid.setCanReorderTiles(true);  
		moduleGrid.setShowAllRecords(true);  
		moduleGrid.setTileDragAppearance(DragAppearance.OUTLINE);   
		moduleGrid.setAnimateTileChange(true); 
		
		modulePicture = new DetailViewerField("picture");
		modulePicture.setType("image");
		modulePicture.setImageWidth(100);  
		modulePicture.setImageHeight(100);
		
		moduleName = new DetailViewerField("name");
		
		moduleGrid.setFields(modulePicture,moduleName);
		//moduleGrid.setFields(new DetailViewerField[] { modulePicture, moduleName});
		
		vStack.addMember(title);
		vStack.addMember(moduleGrid);
		add(vStack);

	}

	@Override
	public void setName(String name) {
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
		moduleGrid.addRecordClickHandler(listener);
	}

	@Override
	public void initTileGrid(ModuleRecord[] records) {
		moduleGrid.setData(records);
		
	}

}
