package com.unilorraine.projetdevie.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.unilorraine.projetdevie.client.ui.tilerecord.CategoryRecord;
import com.google.gwt.user.client.ui.Button;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.layout.VStack;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ActivityGui extends FlowPanel{

	private CategoryRecord[] arrayActRecords;
	private Label labelProjectCategory;
	private Button btnValidate;
	private Button btnBack;
	private TileGrid tileGrid_1; // Activities that have been chosen (in the uper part of the screen)
	private TileGrid tileGrid; // Activities that are not yet chosen (in the right part of the screen)
	private TileGrid tileGrid_3; // All units of choices (down part of the screen in the middle) 
	private TileGrid tileGrid_2; // One unit of selection objects
	private VStack vStack;
	
	public ActivityGui() {
		setSize("470", "320");

		
		vStack = new VStack();
		vStack.setSize("450px", "300px");
		
		labelProjectCategory = new Label("Project -> Category -> Activity");
		vStack.addMember(labelProjectCategory);
		labelProjectCategory.setSize("467px", "24px");
		
		DockPanel dockPanel = new DockPanel();
		dockPanel.setBorderWidth(5);
		dockPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vStack.addMember(dockPanel);
		dockPanel.setSize("450px", "200px");
		
		VStack vStack_1 = new VStack();
		vStack_1.setSize("150", "202");
		
		tileGrid = new TileGrid();
		tileGrid.setSize("148px", "203px");

		tileGrid.setTileWidth(80);
		tileGrid.setTileHeight(80);
        tileGrid.setSelectionType(SelectionStyle.SINGLE);  
        tileGrid.setCanReorderTiles(true);  
        tileGrid.setShowAllRecords(true);  
        tileGrid.setTileDragAppearance(DragAppearance.OUTLINE);   
        tileGrid.setAnimateTileChange(true); 
		tileGrid.setCanDragTilesOut(true);
        vStack_1.addMember(tileGrid);
		dockPanel.add(vStack_1, DockPanel.EAST);

		
		VStack vStack_2 = new VStack();
		vStack_2.setSize("301px", "92px");
		
		tileGrid_1 = new TileGrid();
		tileGrid_1.setWidth("302px");
		tileGrid_1.setTileWidth(80);
		tileGrid_1.setTileHeight(80);
        tileGrid_1.setSelectionType(SelectionStyle.SINGLE);  
        tileGrid_1.setCanReorderTiles(true);  
        tileGrid_1.setShowAllRecords(true);  
        tileGrid_1.setTileDragAppearance(DragAppearance.OUTLINE);   
        tileGrid_1.setAnimateTileChange(true); 
		tileGrid_1.setCanAcceptDrop(true);

		vStack_2.addMember(tileGrid_1);
		dockPanel.add(vStack_2, DockPanel.NORTH);
		
		VStack vStack_3 = new VStack();
		vStack_3.setSize("127px", "101px");
		
		tileGrid_2 = new TileGrid();
		tileGrid_2.setSize("150px", "100px");
		vStack_3.addMember(tileGrid_2);
		dockPanel.add(vStack_3, DockPanel.WEST);
		
		VStack vStack_4 = new VStack();
		vStack_4.setSize("132px", "98px");
		
		tileGrid_3 = new TileGrid();
		tileGrid_3.setSize("150px", "100px");
		vStack_4.addMember(tileGrid_3);
		dockPanel.add(vStack_4, DockPanel.SOUTH);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		vStack.addMember(horizontalPanel);
		
		btnBack = new Button("Back");
		horizontalPanel.add(btnBack);
		btnBack.setWidth("80px");
		
		btnValidate = new Button("Validate");
		horizontalPanel.add(btnValidate);
		btnValidate.setWidth("80px");
		add(vStack);
		
		
	}
	public void setDataGrid(CategoryRecord[] arrayActReccord){
		tileGrid.setData(arrayActReccord);
		tileGrid_1.setData(arrayActReccord);
	}

	
}
