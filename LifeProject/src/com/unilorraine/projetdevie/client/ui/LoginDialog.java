package com.unilorraine.projetdevie.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.widgets.HeaderControl;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VStack;

public class LoginDialog extends Window {
	
	private static final String GRRETINGS = "Pour utiliser l'application connectez-vous a votre compte google";

	ApplicationPanelView.Presenter presenter;
	
	String email;
	private TextBox emailBox;
	
	public LoginDialog(String loginUrl){
		setSize("328px", "213px");
		setTitle("Createur de projet de vie");
		
		//setHeaderControls(new HeaderControl[]{});
		setShowHeaderIcon(false);
		setShowResizeBar(false);
		setShowMinimizeButton(false);
		setShowMaximizeButton(false);
		setShowCloseButton(false);
		setAutoCenter(true);
		setShowModalMask(true);
		setAutoSize(true);
		setShowTitle(true);
		
		
		Anchor signInLink = new Anchor("Connectez-vous!");
		signInLink.setStyleName("gwt-AnchorConnect");
		
		signInLink.setHref(loginUrl);
	    
		Label label = new Label(GRRETINGS);
		label.setStyleName("bigText");
		label.setSize("305px", "100px");
	    addItem(label);
	    addItem(signInLink);
	    signInLink.setWidth("240px");
	    
		/*
		setHeaderControls(new HeaderControl[]{});
		setAutoCenter(true);
		
		setShowModalMask(true);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		addChild(verticalPanel);
		verticalPanel.setSize("379px", "114px");
		
		Label lblNewLabel = new Label("Connectez vous avec votre email : ");
		verticalPanel.add(lblNewLabel);
		lblNewLabel.setHeight("33px");
		
		SimplePanel simplePanel = new SimplePanel();
		verticalPanel.add(simplePanel);
		simplePanel.setHeight("74px");
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		simplePanel.setWidget(horizontalPanel);
		horizontalPanel.setSize("100%", "100%");
		
		Label lblVotre = new Label("Votre Email : ");
		horizontalPanel.add(lblVotre);
		
		emailBox = new TextBox();
		horizontalPanel.add(emailBox);
		emailBox.setWidth("247px");
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		verticalPanel_1.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(verticalPanel_1);
		
		Button connectBtn = new Button("se connecter");
		verticalPanel_1.add(connectBtn);
		
		connectBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				presenter.login(emailBox.getText());
			}
		});
		*/
		

		
	}

	/**
	 * @return the presenter
	 */
	public ApplicationPanelView.Presenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter the presenter to set
	 */
	public void setPresenter(ApplicationPanelView.Presenter presenter) {
		this.presenter = presenter;
	}

	/**
	 * @return the emailBox
	 */
	public TextBox getEmailBox() {
		return emailBox;
	}

	/**
	 * @param emailBox the emailBox to set
	 */
	public void setEmailBox(TextBox emailBox) {
		this.emailBox = emailBox;
	}

	public void reset() {
		System.out.println("Schould be reseted");
		this.emailBox.setText("");
	}

}
