package com.unilorraine.projetdevie.client.ui.binder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPTask;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.IntegerBox;

public class TransitTaskEditor extends AbstractEntityEditor<TransitLPTask> {

	interface Driver extends SimpleBeanEditorDriver<TransitLPTask, TaskEditorBinder> {}
	
	public TransitTaskEditor(){
		super();
		
		VerticalPanel TaskEditorPanel = new VerticalPanel();
		initWidget(TaskEditorPanel);
		TaskEditorPanel.setSize("369px", "213px");
		
		HorizontalPanel namePanel = new HorizontalPanel();
		TaskEditorPanel.add(namePanel);
		
		Label nameLabel = new Label("Nom");
		namePanel.add(nameLabel);
		nameLabel.setWidth("79px");
		
		TextBox name = new TextBox();
		namePanel.add(name);
		name.setWidth("234px");
		
		HorizontalPanel descriptionPanel = new HorizontalPanel();
		TaskEditorPanel.add(descriptionPanel);
		descriptionPanel.setHeight("84px");
		
		Label descritpionLabel = new Label("Description");
		descriptionPanel.add(descritpionLabel);
		descritpionLabel.setWidth("81px");
		
		TextArea description = new TextArea();
		descriptionPanel.add(description);
		description.setSize("260px", "79px");
		
		HorizontalPanel progressStatusPanel = new HorizontalPanel();
		TaskEditorPanel.add(progressStatusPanel);
		progressStatusPanel.setWidth("332px");
		
		HorizontalPanel progressPanel = new HorizontalPanel();
		progressStatusPanel.add(progressPanel);
		
		Label progressLabel = new Label("Progr\u00E8s");
		progressPanel.add(progressLabel);
		progressLabel.setWidth("81px");
		
		IntegerBox progress = new IntegerBox();
		progressPanel.add(progress);
		
		CheckBox status = new CheckBox("Status");
		progressStatusPanel.add(status);
		status.setWidth("99px");
		
		HorizontalPanel linkPanel = new HorizontalPanel();
		TaskEditorPanel.add(linkPanel);
		
		Label imageLinkLabel = new Label("Lien image");
		linkPanel.add(imageLinkLabel);
		imageLinkLabel.setWidth("83px");
		
		TextBox imageLink = new TextBox();
		linkPanel.add(imageLink);
		imageLink.setWidth("251px");
	}
	
	@Override
	protected SimpleBeanEditorDriver<TransitLPTask, AbstractEntityEditor<TransitLPTask>> setDriver() {
		return GWT.create(Driver.class);
	}

}
