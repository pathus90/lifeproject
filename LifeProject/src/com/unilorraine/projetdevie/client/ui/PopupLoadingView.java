package com.unilorraine.projetdevie.client.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;


public final class PopupLoadingView extends PopupPanel
{
    private final FlowPanel container = new FlowPanel();

    public PopupLoadingView()
    {        
        final Image ajaxImage = new Image("http://openclipart.org/image/34px/svg_to_png/173360/1353901897.png");
        final Grid grid = new Grid(1, 2);  
        grid.setWidget(0, 0, ajaxImage);
        grid.setText(0, 1, "Charge les donn\u00E9es...");    
        this.container.add(grid);
        add(this.container);   
        
        /*
         * setPopupPositionAndShow(new PopupPanel.PositionCallback() {
            public void setPosition(int offsetWidth, int offsetHeight) {
                    int left = ((Window.getClientWidth() - offsetWidth) / 2) >> 0;
                    int top = ((Window.getClientHeight() - offsetHeight) / 2) >> 0;
                    setPopupPosition(left, top);
            }
    });
         */
        
    }

    public Widget asWidget()
    {
        return this;
    }

    public void stopProcessing()
    {
        hide();
    }

    public void startProcessing()
    {
        center();
        show();
    }

    public void showWidget()
    {
        startProcessing();
    }
}
