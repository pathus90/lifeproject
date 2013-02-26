package com.unilorraine.projetdevie.client.shared.transitentities;

public class TransitLPUser extends AbstractTransitLPAccount{

	/**
	 * The id for the active project, this wont be updated over transit
	 */
	private String activeProject;
	
	/**
	 * The id for the building project, this wont be updated over transit
	 */
	private String buildingProject;
	
	public TransitLPUser(String id, String lastname, String firstname,
			String email, String description, String pictureLink, String activeProject, String buildingProject) {
		super(id, lastname, firstname, email, description, pictureLink);
		
		this.activeProject = activeProject;
		this.buildingProject = buildingProject;
	}

	public TransitLPUser() {
		super();
		activeProject = "";
		buildingProject = "";
	}

	public String getActiveProject() {
		return activeProject;
	}

	public void setActiveProject(String activeProject) {
		this.activeProject = activeProject;
	}

	public String getBuildingProject() {
		return buildingProject;
	}

	public void setBuildingProject(String buildingProject) {
		this.buildingProject = buildingProject;
	}

}
