package com.unilorraine.projetdevie.client.ui.tilerecord;

import com.smartgwt.client.widgets.tile.TileRecord;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;

public class ActivityRecord extends TileRecord{
	  
    public ActivityRecord(TransitLPActivity activity) {  
        setName(activity.getName());  
        setPicture(activity.getImageLink());  
        setDescription(activity.getDescription()); 
        setId(activity.getId());
        
        setTransit(activity);
    }  
  
    /** 
     * Set the transit. 
     * 
     * @param name the name 
     */  
    public void setTransit(TransitLPActivity activity) {  
        setAttribute("transit", activity);  
    }  
    
    /** 
     * Return the transit. 
     * 
     * @return the name 
     */  
    public TransitLPActivity getTransit() {  
        return (TransitLPActivity)getAttributeAsObject("transit");  
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
     * Set the description. 
     * 
     * @param description the description 
     */  
    public void setDescription(String description) {  
        setAttribute("description", description);  
    }  
  
    /** 
     * Return the description. 
     * 
     * @return the description 
     */  
    public String getDescription() {  
        return getAttribute("description");  
    }
    
    public void setId(String name) {  
        setAttribute("id", name);  
    } 
    
    public String getId(String name){
    	return getAttribute("id");
    }
}
