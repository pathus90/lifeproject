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
package com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;

/**
 * Sample implementation of {@link CategoryModuleView}.
 */
public class CategoryModuleViewImpl extends FlowPanel implements CategoryModuleView {
	
	private Presenter listener;
	private DetailViewerField categoryPicture;
	private DetailViewerField categoryName;
	private TileGrid tileGrid;
	private VStack vStack;
	
	
	public CategoryModuleViewImpl() {
		
		vStack = new VStack();
		
		Label title = new Label("Choisissez une cat\u00E9gorie");
		vStack.addMember(title);
		title.setWidth("336px");
		title.setStyleName("title");
		
		
		tileGrid = new TileGrid();
		tileGrid.setSize("450px", "450px");
		tileGrid.setEdgeSize(0);
		tileGrid.setShowEdges(false);
		
		tileGrid.setTileWidth(100);
		tileGrid.setTileHeight(100);
		tileGrid.setSelectionType(SelectionStyle.SINGLE);  
		 
		 categoryPicture = new DetailViewerField("picture");
		 categoryPicture.setType("image");  
		 categoryPicture.setImageWidth(80);  
		 categoryPicture.setImageHeight(80); 
		 
		 categoryName = new DetailViewerField("name");
		 tileGrid.setFields(categoryPicture,categoryName);
		 vStack.addMember(tileGrid);
		add(vStack);
		

	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
		tileGrid.addRecordClickHandler(listener);
	}

	@Override
	public void initTileGrid(CategoryRecord[] arrayCatReccord) {
		tileGrid.setData(arrayCatReccord);
	}

}
