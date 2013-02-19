package com.unilorraine.projetdevie.client.shared.jdoentities.accountentities;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPProject;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPUser;

/**
 * JDO db class represents the Autistic people called Users in the application
 * @author Christophe
 *
 */
@PersistenceCapable
public class LPUser extends AbstractLPAccounts<TransitLPUser> {

	
	/**
	 * List of the old projects
	 */
	@Persistent
	private ArrayList<LPProject> archivProject;
	
	/**
	 * The active Project
	 */
	@Persistent
	private LPProject activeProject;
	
	/**
	 * The next project( can be under construction)
	 */
	@Persistent
	private LPProject buildingProject;
	
	/**
	 * Basic constructor for a User account
	 * @param lastname
	 * @param firstname
	 * @param email
	 * @param description
	 * @param pictureLink
	 * @param instution
	 */
	public LPUser(String lastname, String firstname, String email,
			String description, String pictureLink) {
		super(lastname, firstname, email, description, pictureLink);
		archivProject = new ArrayList<LPProject>();
		activeProject = null;
		buildingProject = null;
	}
	
	public LPUser() {
		this("", "", "", "", "");
	}

	/**
	 * Get the current project
	 * @return returns the current project, null if none in the DB
	 */
	public LPProject getActiveProject() {
		return activeProject;
	}

	public void setActiveProject(LPProject activeProject) {
		this.activeProject = activeProject;
	}

	/**
	 * Get the next project (can be under construction)
	 * @return returns the next project, null if none in the DB 
	 */
	public LPProject getBuildingProject() {
		return buildingProject;
	}

	public boolean addProjectToArchive(LPProject e) {
		return archivProject.add(e);
	}

	public int indexOfArchivProject(Object o) {
		return archivProject.indexOf(o);
	}

	public ListIterator<LPProject> archivProjectIterator() {
		return archivProject.listIterator();
	}

	public LPProject removeArchiveProject(int index) {
		return archivProject.remove(index);
	}

	public boolean removeArchiveProject(Object o) {
		return archivProject.remove(o);
	}

	public int size() {
		return archivProject.size();
	}

	public void setBuildingProject(LPProject buildingProject) {
		this.buildingProject = buildingProject;
	}

	@Override
	public boolean updateFromTransit(TransitLPUser transitEntity) {
		return helperUpdateFromTransit(transitEntity);
	}

	@Override
	public TransitLPUser createTransit() {
		TransitLPUser transit = new TransitLPUser();
		helperFillTransit(transit);
		transit.setActiveProject(getActiveProject().getId());
		transit.setBuildingProject(getBuildingProject().getId());
		return transit;
	}
	
}
