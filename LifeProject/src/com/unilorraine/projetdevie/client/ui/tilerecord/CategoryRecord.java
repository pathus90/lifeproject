package com.unilorraine.projetdevie.client.ui.tilerecord;

import com.smartgwt.client.widgets.tile.TileRecord;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPProject;

public class CategoryRecord extends TileRecord {
 
  
	public CategoryRecord(TransitLPCategory category){
        this(category.getName(), category.getImageLink(), null, category.getId());		
	}
	public CategoryRecord(TransitLPProject project){
        this(project.getName(), project.getImageLink(), null, project.getId());		
	}
    public CategoryRecord(String name, String picture, String description,String id) {  
        setName(name);  
        setPicture(picture);  
        setDescription(description); 
        setId(id);
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
