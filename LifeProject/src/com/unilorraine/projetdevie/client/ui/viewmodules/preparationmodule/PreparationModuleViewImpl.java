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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.google.gwt.user.client.ui.VerticalSplitPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.google.gwt.user.client.ui.SimpleRadioButton;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.smartgwt.client.widgets.ViewLoader;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects.UnitPanel;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects.UnitTileGrid;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects.UnitTileGridListener;
import com.smartgwt.client.widgets.tile.TileLayout;
import com.google.gwt.user.client.ui.Grid;
import com.smartgwt.client.widgets.EdgedCanvas;

/**
 * Sample implementation of {@link PreparationModuleView}.
 */
public class PreparationModuleViewImpl extends FlowPanel implements PreparationModuleView {
	
	private Presenter listener;

	private DetailViewerField categoryPicture;
	private DetailViewerField categoryName;
	private DetailViewerField categoryChoice;
	
	private TileGrid tileGridActivities;
	
	private ListGrid listGrid;
	
	private VerticalPanel unitPanel;
	
	private UnitPanel choicePanel;
	private Label categoryLabel;
	
	private Window savedWindow;
	
	public PreparationModuleViewImpl() {
		
		DockPanel dockPanel = new DockPanel();
		add(dockPanel);
		dockPanel.setSize("566px", "484px");
		
		VerticalPanel titlePanel = new VerticalPanel();
		dockPanel.add(titlePanel, DockPanel.NORTH);
		
		Label title = new Label("Pr\u00E9paration du projet de vie");
		title.setStyleName("title");
		titlePanel.add(title);
		title.setHeight("61px");
		
		VerticalPanel verticalPanelProject = new VerticalPanel();
		dockPanel.add(verticalPanelProject, DockPanel.WEST);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanelProject.add(horizontalPanel);
		//categoryChoice = new DetailViewerField("choice");
		
		Label preparationLabel = new Label("Editez la cat\u00E9gorie : ");
		horizontalPanel.add(preparationLabel);
		preparationLabel.setWidth("177px");
		preparationLabel.setStyleName("subtitle");
		
		categoryLabel = new Label("");
		categoryLabel.setStyleName("subtitle");
		horizontalPanel.add(categoryLabel);
		
		categoryPicture = new DetailViewerField("picture");
		categoryPicture.setType("image");  
		categoryPicture.setImageWidth(50);  
		categoryPicture.setImageHeight(50); 
		
		categoryName = new DetailViewerField("name");
		
		DecoratedStackPanel decoratedStackPanel = new DecoratedStackPanel();
		verticalPanelProject.add(decoratedStackPanel);
		decoratedStackPanel.setSize("303px", "381px");
		
		tileGridActivities = new TileGrid();
		tileGridActivities.setSize("292px", "432px");
		
		tileGridActivities.setEdgeSize(0);
		tileGridActivities.setShowEdges(false);
		
		tileGridActivities.setTileWidth(80);
		tileGridActivities.setTileHeight(80);
		
		tileGridActivities.setCanAcceptDrop(true);  
		tileGridActivities.setCanDrag(true);
		
		tileGridActivities.setFields(categoryPicture,categoryName);
		tileGridActivities.setData(new ActivityRecord[10]);
		
		
		decoratedStackPanel.add(tileGridActivities, "Activit\u00E9s du Projet de Vie", false);
		
		ScrollPanel scrollPanel = new ScrollPanel();
		decoratedStackPanel.add(scrollPanel, "Unit\u00E9s de Choix", false);
		scrollPanel.setSize("300px", "443px");
		
		unitPanel = new VerticalPanel();
		scrollPanel.setWidget(unitPanel);
		unitPanel.setSize("292px", "443px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		dockPanel.add(verticalPanel, DockPanel.WEST);
		
		Label schemaLabel = new Label("Activit\u00E9s restantes");
		schemaLabel.setStyleName("subtitle");
		verticalPanel.add(schemaLabel);
		
		listGrid = new ListGrid();  
        listGrid.setSize("240px", "504px");
        listGrid.setCanDragRecordsOut(true);  
        listGrid.setCanAcceptDroppedRecords(true);  
        listGrid.setCanReorderRecords(true);  
  
        ListGridField nameField = new ListGridField("name");  
        ListGridField descriptionField = new ListGridField("description");  
        //lifeSpanField.setWidth(50);    
        listGrid.setFields(nameField, descriptionField);
        verticalPanel.add(listGrid);
		
        listGrid.setData(new ActivityRecord[10]);
        
        Button btnSauverLeProjet = new Button("Sauver le projet");
        btnSauverLeProjet.addClickHandler(new ClickHandler() {
        	public void onClick(ClickEvent event) {
        		listener.saveProject();
        	}
        });
        verticalPanel.add(btnSauverLeProjet);


        savedWindow = new Window();
        
        savedWindow.setAutoCenter(true);
        savedWindow.setShowModalMask(true);
        savedWindow.setSize("200px", "150px");
        savedWindow.setShowTitle(true);
        savedWindow.setShowMinimizeButton(false);
        savedWindow.setTitle("Projet sauvegard\u00E9");
        Label label =  new Label("Le projet a \u00E9t\u00E9 sauv\u00E9");
        
        label.setStyleName("bigText");
        savedWindow.addItem(label);
        
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void setText(String text) {
		//textBox.setText(text);
	}

	@Override
	public void setProjectActivities(ActivityRecord[] activities) {
		tileGridActivities.setData(activities);
		
	}

	@Override
	public void setSchemaActivities(ActivityRecord[] activities) {
		listGrid.setData(activities);
	}

	@Override
	public void addExistingUnitChoice(String id, ActivityRecord[] activities) {
		UnitPanel panel = new UnitPanel();
		
		panel.setUnitID(id);
		panel.setActivitiesRecords(activities);
		panel.setTileListener(listener);
		
		this.unitPanel.add(panel);
		
	}

	@Override
	public void addNewUnitChoice() {
		
		UnitPanel newPanel = new UnitPanel();
		newPanel.setTileListener(listener);
		
		this.unitPanel.add(newPanel);
		
	}

	@Override
	public void removeGrid(UnitPanel unitPanel) {
		this.unitPanel.remove(unitPanel);
		
	}

	@Override
	public List<Record> getProjectActivities() {
		ArrayList<Record> list = new ArrayList<Record>();
		for(Record rec : tileGridActivities.getData()){
				list.add(rec);
		}
		return list;
	}

	@Override
	public List<List<Record>> getUnitChoices() {
		ArrayList<List<Record>> units = new ArrayList<List<Record>>();
		
		for(int i = 0; i < unitPanel.getWidgetCount(); i++){
			if(unitPanel.getWidget(i) instanceof UnitPanel){
				UnitPanel panel = (UnitPanel)unitPanel.getWidget(i);
				if(panel.getActivitiesRecords().length > 0){
					ArrayList<Record> list = new ArrayList<Record>();
					for(Record rec : panel.getActivitiesRecords()){
							list.add(rec);
					}
					units.add(list);
				}
			}
		}
		
		return units;
	}

	@Override
	public void setCategory(String category) {
		this.categoryLabel.setText(SafeHtmlUtils.fromString(category).asString());
	}

	@Override
	public void showSaved() {
		savedWindow.show();
	}
}
