package com.unilorraine.projetdevie.client.ui.tilerecord;

import sun.reflect.generics.visitor.Reifier;

import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.tile.TileRecord;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.ui.viewmodules.RegisterableModule;

/**
 * A tile record for the registerable modules of the app. <br/>
 * The {@link RegisterableModule} is stored under the attribute "module", the name under "name" and the picture link under "picture".
 * This will use the name and the picture link to create tiles for a {@link TileGrid}
 * @author Christophe
 *
 */
public class ModuleRecord extends TileRecord {
	
	public ModuleRecord(RegisterableModule module){
        setName(module.getModuleName());
        setPicture(module.getModulePictureLink());
        
        setModule(module);
        
	}
   
    
	/** 
     * Set the app module. 
     * 
     * @param appModule the module to be set
     */  
    public void setModule(RegisterableModule appModule) {  
        setAttribute("module", appModule);  
    }  
    
    /** 
     * Return the module {@link RegisterableModule}
     * 
     * @return the module 
     */  
    public RegisterableModule getModule() {  
        return (RegisterableModule)getAttributeAsObject("module");  
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
}
