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
package com.unilorraine.projetdevie.client.ui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.smartgwt.client.widgets.viewer.DetailViewer;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;



/**
 * Sample implementation of {@link CategoryView}.
 */
public class CategoryViewImpl extends FlowPanel implements CategoryView {
	private Presenter listener;
	private TileGrid tileGrid;
	private VStack vStack;
	private DetailViewerField categoryPicture;
	private DetailViewerField categoryName;
	private DetailViewerField categoryChoice;
	private CategoryRecord[] tiles;
	
	public CategoryViewImpl() {
		
		vStack = new VStack();
		vStack.setSize("448px", "298px");
		
		
		tileGrid = new TileGrid();
		
		tileGrid.setHeight("295px");
		tileGrid.setTileWidth(80);
		tileGrid.setTileHeight(80);
        tileGrid.setSelectionType(SelectionStyle.SINGLE);  
        tileGrid.setCanReorderTiles(true);  
        tileGrid.setShowAllRecords(true);  
        tileGrid.setTileDragAppearance(DragAppearance.OUTLINE);   
        tileGrid.setAnimateTileChange(true); 
        
        categoryPicture = new DetailViewerField("picture");
        categoryPicture.setType("image");  
//        categoryPicture.setImageURLPrefix("cars/");  
        categoryPicture.setImageWidth(50);  
        categoryPicture.setImageHeight(50); 
        
        categoryName = new DetailViewerField("name");
        categoryChoice = new DetailViewerField("choice");
        tileGrid.setFields(categoryPicture, categoryName,categoryChoice);
		
		vStack.addMember(tileGrid);
		add(vStack);

	}

	@Override
	public void setName(String name) {

	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void initTileGrid(CategoryRecord[] arrayCatReccord) {
		tileGrid.setData(arrayCatReccord);
		
	}

}
