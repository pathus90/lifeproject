package com.unilorraine.projetdevie.client.shared.jdoentities.pots;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.jdoentities.IDBEntity;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivity;
import com.unilorraine.projetdevie.client.shared.transitentities.ITransitEntity;

/**
 * Implementation of the {@link AbstractLPPot} for LPActivity. This pot can store activites and is mainly used for enclosing schemas.
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPPotActivity extends AbstractLPPot<LPActivity> {

	@Persistent
	private List<LPActivity> storedEntities;
	
	/** {@inheritDoc} */
	public LPPotActivity() {
		super();
	}

	/** {@inheritDoc} */
	public LPPotActivity(String name, String description,
			List<LPActivity> storedEntites, List<String> linkedEntities) {
		super(name, description, storedEntites, linkedEntities);
	}

	/** {@inheritDoc} */
	public LPPotActivity(String name, String description) {
		super(name, description);
	}

	@Override
	protected List<LPActivity> getStoredEntities() {
		return storedEntities;
	}

	@Override
	protected void setStoredEntities(List<LPActivity> storedEntities) {
		this.storedEntities = storedEntities;

	}

}
