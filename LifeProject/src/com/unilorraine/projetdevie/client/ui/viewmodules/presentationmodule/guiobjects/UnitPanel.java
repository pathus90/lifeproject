package com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule.guiobjects;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.EdgedCanvas;
import com.smartgwt.client.widgets.events.DragStopEvent;
import com.smartgwt.client.widgets.events.DragStopHandler;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.tile.TileRecord;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivityUnit;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;

public class UnitPanel extends EdgedCanvas {

	private String WIDTH = "250px";
	private String HEIGHT = "210px";
	
	private String unitID = "";
	
	private UnitTileGridListener listener;

	private UnitTileGrid grid;
	
	public UnitPanel() {
		setSize(WIDTH, HEIGHT);
		grid = new UnitTileGrid(WIDTH , HEIGHT);
		
		grid.addDropHandler(new DropHandler() {
					
			@Override
			public void onDrop(DropEvent event) {
				//System.out.println(count() + " < " + LPActivityUnit.MAX_ITEM);
				if(event.getSource() instanceof UnitTileGrid){

					UnitTileGrid thisGrid = (UnitTileGrid)event.getSource();
					System.out.println("Is of type, length : " + thisGrid.getData().length );
					if(thisGrid.getData().length < LPActivityUnit.MAX_ITEM){
						if(thisGrid.getData().length == 0){
							listener.needNewGrid();
						}
					}else{
						event.cancel();
						System.out.println("Unit full");
					}
				}
				
					
			}
		});
		
		grid.addDragStopHandler(new DragStopHandler() {
			
			@Override
			public void onDragStop(DragStopEvent event) {
				if(event.getSource() instanceof UnitTileGrid){

					UnitTileGrid thisGrid = (UnitTileGrid)event.getSource();
					System.out.println("Is of type, length : " + thisGrid.getData().length );
					if(thisGrid.getData().length == 0){
						System.out.println("Delete Grid");
						listener.deleteGrid(getThis());
					}
				}
				
			}
		});
		
		addChild(grid);
	}
	
	public Record[] getActivitiesRecords(){
		return grid.getData();
	}
	
	public void setActivitiesRecords(ActivityRecord[] records){
		grid.setData(records);
	}
	
	/**
	 * @return the unitID
	 */
	public String getUnitID() {
		return unitID;
	}

	/**
	 * @param unitID the unitID to set
	 */
	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}

	/**
	 * @return the tileListener
	 */
	public UnitTileGridListener getTileListener() {
		return listener;
	}

	/**
	 * @param tileListener the tileListener to set
	 */
	public void setTileListener(UnitTileGridListener tileListener) {
		this.listener = tileListener;
	}

	private UnitPanel getThis(){
		return this;
	}
	
}
