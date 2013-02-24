package com.unilorraine.projetdevie.client.shared.jdoentities.pots;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.IDBEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPCategory;

@PersistenceCapable
public class LPPotCategory extends AbstractLPPot<TransitLPCategory, LPCategory> {

	@Persistent
	private List<LPCategory> storedEntities;
	
	@Override
	protected List<LPCategory> getStoredEntities() {
		return storedEntities;
	}

	@Override
	protected void setStoredEntities(List<LPCategory> storedEntities) {
		this.storedEntities = storedEntities;
		
	}

	@Override
	public LPCategory getInstanceOfStoredEntity() {
		return new LPCategory();
	}


}
