package com.unilorraine.projetdevie.client.shared.jdoentities.pots;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.jdoentities.accountentities.LPUserGroup;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPCategory;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUserGroup;

@PersistenceCapable
public class LPPotUserGroup extends
		AbstractLPPot<TransitLPUserGroup, LPUserGroup> {

	@Persistent
	private List<LPUserGroup> storedEntities;
	
	@Override
	protected List<LPUserGroup> getStoredEntities() {
		return storedEntities;
	}

	@Override
	protected void setStoredEntities(List<LPUserGroup> storedEntities) {
		this.storedEntities = storedEntities;

	}

	@Override
	public LPUserGroup getInstanceOfStoredEntity() {
		return new LPUserGroup();
	}

}
