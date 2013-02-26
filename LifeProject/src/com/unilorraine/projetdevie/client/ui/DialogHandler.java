package com.unilorraine.projetdevie.client.ui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.UIObject;

public class DialogHandler implements ClickHandler {

	
	@Override
	public void onClick(ClickEvent event) {
	    final DialogBox dialog = new DialogBox(true);
	    String text = "Message....";
	    dialog.setText(text);
	    
	    Button button2 = new Button("Close");
	    button2.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        dialog.hide();
	      }
	    });
	    dialog.add(button2);
	    UIObject button = (UIObject) event.getSource();
	    int x = button.getAbsoluteLeft() + 100;
	    int y = button.getAbsoluteTop() - 100;
	    dialog.setPopupPosition(x, y);
	    dialog.setAnimationEnabled(true);
	    dialog.setWidth("350px");
	    dialog.show();
		
	}


}
