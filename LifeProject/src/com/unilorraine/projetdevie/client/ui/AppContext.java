package com.unilorraine.projetdevie.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.unilorraine.projetdevie.client.shared.jdoentities.pots.AbstractLPPot;
import com.unilorraine.projetdevie.client.shared.transitentities.AbstractTransitLPAccount;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActor;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;

/**
 * An App Context is the context in which a module can be run. It is hold by the {@link ApplicationPanelView} implementation and is passed on the module.
 * As of now a AppContext is composed of an active user and a list of entity ids that links this app to a particular context. 
 * This links are used with {@link AbstractLPPot}, so the module know with which Pots it has to work.
 * @author Christophe
 *
 */
public class AppContext {

	/**
	 * The active User
	 */
	private AbstractTransitLPAccount activeAccount;
	
	/**
	 * The context links for app
	 */
	private List<String> contextLinks;

	/**
	 * Basic constructor for an app context
	 * @param activeAccount the account currently log in or active
	 * @param contextLinks entity ids that links this app to a particular context, used for the Pot fetching 
	 */
	public AppContext(AbstractTransitLPAccount activeAccount, List<String> contextLinks) {
		super();
		this.activeAccount = activeAccount;
		if(contextLinks != null)
			this.contextLinks = contextLinks;
		else
			this.contextLinks = new ArrayList<String>();
	}

	/**
	 * get the account currently log in or active 
	 * @return
	 */
	public AbstractTransitLPAccount getActiveAccount() {
		return activeAccount;
	}

	/**
	 * set the account currently log in or active 
	 * @param activeUser
	 */
	public void setActiveAccount(AbstractTransitLPAccount activeAccount) {
		this.activeAccount = activeAccount;
	}

	/**
	 * This links are used with {@link AbstractLPPot}, so the module know with which Pots it has to work.
	 * @return
	 */
	public List<String> getContextLinks() {
		return contextLinks;
	}

	/**
	 * This links are used with {@link AbstractLPPot}, so the module know with which Pots it has to work.
	 * @param contextLinks the links building the app context
	 */
	public void setContextLinks(List<String> contextLinks) {
		this.contextLinks = contextLinks;
	}
	
	
	
}
