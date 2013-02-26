package com.unilorraine.projetdevie.client.ui.tilerecord;

import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.TileRecord;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;

/**
 * A tile record for the registerable modules of the app.
 * This will use the name and the picture link to create tiles for a {@link TileGrid}
 * @author Christophe
 *
 */
public class ModuleRecord extends TileRecord {

	private RegisterableModule module;
	
	public ModuleRecord(RegisterableModule module){
        this(module.getModuleName(), module.getModulePictureLink());		
	}
   
    public ModuleRecord(String name, String picture) {  
        setName(name);  
        setPicture(picture);  
    }  
    
    /** 
     * Set the name. 
     * 
     * @param name the name 
     */  
    public void setName(String name) {  
        setAttribute("name", name);  
    }  
    
    /** 
     * Return the name. 
     * 
     * @return the name 
     */  
    public String getName() {  
        return getAttribute("name");  
    }  
  
  
    /** 
     * Set the picture. 
     * 
     * @param picture the picture 
     */  
    public void setPicture(String picture) {  
        setAttribute("picture", picture);  
    }  
  
    /** 
     * Return the picture. 
     * 
     * @return the picture 
     */  
    public String getPicture() {  
        return getAttribute("picture");  
    }

    /**
     * Get the linked Module
     * @return the module in this record
     */
	public RegisterableModule getModule() {
		return module;
	}  
}
