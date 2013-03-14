package com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivityUnit;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;

public class UnitTileGrid extends TileGrid {
	
	
	private DetailViewerField categoryPicture;
	private DetailViewerField categoryName;
	
	public UnitTileGrid(String width, String height) {
		super();
		setSize(width, height);
		
		setEdgeSize(0);
		setShowEdges(false);
		
		categoryPicture = new DetailViewerField("picture");
		categoryPicture.setType("image");  
		categoryPicture.setImageWidth(50);  
		categoryPicture.setImageHeight(50); 
		 
		categoryName = new DetailViewerField("name");
		
		setFields(categoryPicture, categoryName);
		
		setTileWidth(80);
		setTileHeight(80);
		
		setCanAcceptDrop(true);  
		setCanDrag(true);
		
		setData(new ActivityRecord[4]);
	}
		
	private void newFirstUnit(){
		
	}
}
