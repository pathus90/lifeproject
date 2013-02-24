package com.unilorraine.projetdevie.client.shared.jdoentities.pots;

import com.unilorraine.projetdevie.client.service.helperinterfaces.IPotService;
import com.unilorraine.projetdevie.client.shared.jdoentities.AbstractLPEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;

/**
 * Factory to create a Pot based on its class. Is needed for a generic {@link IPotService}
 * @author Christophe
 *
 */
public final class PotFactory{

	//TODO Comment need to be redone
	/**
	 * Get an instance of the passed class of the entity stored in the pot, not the class of the pot it self!
	 * @param clazz the pot class that ois needed
	 * @return the instantiated class or null if this class does not exist in the factory
	 */
	public final static AbstractLPPot getInstanceOf(String canonicalClassName){
		if(canonicalClassName.equals(LPActivity.class.getCanonicalName()))
			return new LPPotActivity();
		return null;
	}
}
