package com.unilorraine.projetdevie.client.ui.viewmodules;

//TODO comment
public abstract class AbstractModuleRegister implements ModuleRegister {

	protected String name;
	
	protected String pictureLink;
	
	public AbstractModuleRegister(String name, String pictureLink) {
		this.name = name;
		this.pictureLink = pictureLink;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPictureLink() {
		return pictureLink;
	}

}
