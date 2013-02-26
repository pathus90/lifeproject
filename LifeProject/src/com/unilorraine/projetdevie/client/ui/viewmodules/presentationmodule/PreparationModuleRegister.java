package com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule;

import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractModuleRegister;
import com.unilorraine.projetdevie.client.ui.viewmodules.AppModule;

//TODO to comment
public class PreparationModuleRegister extends AbstractModuleRegister {

	public PreparationModuleRegister() {
		super("Preparation Module", "http://openclipart.org/image/128px/svg_to_png/67/Andy_Tools_Hammer_Spanner.png");
	}
	
	@Override
	public AppModule getModule() {
		 return new PreparationModuleActivity();
	}


}
