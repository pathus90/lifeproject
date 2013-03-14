/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.unilorraine.projetdevie.client.ui.viewmodules.unitchoosermodule;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.DockPanel;
import com.smartgwt.client.widgets.tile.TileLayout;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import com.google.gwt.user.client.ui.Grid;
import com.smartgwt.client.widgets.events.DropEvent;
import com.smartgwt.client.widgets.events.DropHandler;
import com.smartgwt.client.widgets.layout.FlowLayout;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ViewLoader;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.smartgwt.client.widgets.EdgedCanvas;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.unilorraine.projetdevie.client.shared.jdoentities.projectentites.LPActivityUnit;
import com.unilorraine.projetdevie.client.ui.PopupLoadingView;
import com.unilorraine.projetdevie.client.ui.tilerecord.ActivityRecord;
import com.unilorraine.projetdevie.client.ui.viewmodules.preparationmodule.guiobjects.UnitTileGrid;
import com.google.gwt.user.client.ui.DisclosurePanel;

/**
 * Sample implementation of {@link UnitChooserView}.
 */
public class UnitChooserViewImpl extends FlowPanel implements UnitChooserView {
	
	private Presenter listener;
	private TileGrid unitChoiceGrid;
	private TileGrid targetGrid;
	private TileGrid activityGrid;
	
	private DetailViewerField viewerPictureSmall;
	private DetailViewerField viewerPicture;
	private DetailViewerField viewerName;
	
	private PopupLoadingView loading = new PopupLoadingView();
	
	private DialogBox box;

	public UnitChooserViewImpl() {
		
		viewerPictureSmall = new DetailViewerField("picture");
		viewerPictureSmall.setType("image");  
		viewerPictureSmall.setImageWidth(50);  
		viewerPictureSmall.setImageHeight(50); 
		
		viewerPicture = new DetailViewerField("picture");
		viewerPicture.setType("image");  
		viewerPicture.setImageWidth(100);  
		viewerPicture.setImageHeight(100);
		 
		viewerName = new DetailViewerField("name");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		add(verticalPanel);
		verticalPanel.setSize("602px", "602px");
		
		Label title = new Label("Cr\u00E9ateur commun du projet de vie");
		title.setStyleName("title");

		verticalPanel.add(title);
		
		
		EdgedCanvas edgedCanvasChoice = new EdgedCanvas();
		edgedCanvasChoice.setSize("590px", "150px");
		
		unitChoiceGrid = new TileGrid();
		unitChoiceGrid.setSize("590px", "135px");
		unitChoiceGrid.setSelectionType(SelectionStyle.SINGLE);
		unitChoiceGrid.setFields(viewerPicture,viewerName);
		
		unitChoiceGrid.setEdgeSize(0);
		unitChoiceGrid.setShowEdges(false);
		
		unitChoiceGrid.setCanAcceptDrop(true);  
		unitChoiceGrid.setCanDrag(true);
		
		unitChoiceGrid.setTileWidth(120);
		unitChoiceGrid.setTileHeight(120);
		
		edgedCanvasChoice.addChild(unitChoiceGrid);
		verticalPanel.add(edgedCanvasChoice);
		
		VerticalPanel middlePanel = new VerticalPanel();
		middlePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		middlePanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.add(middlePanel);
		middlePanel.setSize("602px", "155px");
		
		EdgedCanvas edgedCanvas = new EdgedCanvas();
		edgedCanvas.setSize("150px", "150px");
		edgedCanvas.setAlign(Alignment.CENTER);
		
		Label dropText = new Label("La cible");
		
		targetGrid = new TileGrid();
		targetGrid.setSize("125px", "125px");
		targetGrid.setSelectionType(SelectionStyle.SINGLE);
		targetGrid.setFields(viewerPicture,viewerName);
		
		targetGrid.setEdgeSize(0);
		targetGrid.setShowEdges(false);
		
		targetGrid.setCanAcceptDrop(true);  
		targetGrid.setCanDrag(true);
		
		targetGrid.setTileWidth(120);
		targetGrid.setTileHeight(120);
		targetGrid.setData(new ActivityRecord[1]);
		
		targetGrid.addDropHandler(new DropHandler() {
			
				@Override
				public void onDrop(DropEvent event) {
					if(targetGrid.getData().length >= 1){
						event.cancel();
						System.out.println("Target full");
					}	
				}
			});
		
		edgedCanvas.addChild(targetGrid);
		middlePanel.add(dropText);
		middlePanel.add(edgedCanvas);
		
		DisclosurePanel disclosurePanel = new DisclosurePanel("Activites choisies");
		verticalPanel.add(disclosurePanel);
		disclosurePanel.setWidth("586px");
		
		ScrollPanel scrollPanel = new ScrollPanel();
		disclosurePanel.setContent(scrollPanel);
		scrollPanel.setSize("581px", "112px");
		
		activityGrid = new TileGrid();
		activityGrid.setSize("572px", "106px");
		activityGrid.setSelectionType(SelectionStyle.NONE);
		activityGrid.setFields(viewerPictureSmall,viewerName);
		
		activityGrid.setEdgeSize(0);
		activityGrid.setShowEdges(false);
		
		activityGrid.setCanAcceptDrop(false);  
		activityGrid.setCanDrag(false);
		
		activityGrid.setTileWidth(80);
		activityGrid.setTileHeight(80);
		
		scrollPanel.setWidget(activityGrid);
		
		box = new DialogBox();
		
		// Set the dialog box's caption.
		box.setText("My First Dialog");

        // Enable animation.
		box.setAnimationEnabled(true);

        // Enable glass background.
		box.setGlassEnabled(true);

        // DialogBox is a SimplePanel, so you have to set its widget 
        // property to whatever you want its contents to be.
        Button ok = new Button("OK");
        
        ok.addClickHandler(new ClickHandler() {
           public void onClick(ClickEvent event) {
        	   box.hide();
           }
        });

        Label label = new Label("Il n'y a plus d'unit\u00E9 de choix");

        VerticalPanel panel = new VerticalPanel();
        panel.setHeight("100");
        panel.setWidth("300");
        panel.setSpacing(10);
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(label);
        panel.add(ok);

        box.setWidget(panel);
		
		
		
		
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
		targetGrid.addRecordDoubleClickHandler(listener);
	}

	@Override
	public void setUnitContainer(Record[] units) {
		unitChoiceGrid.setData(units);
		
	}

	@Override
	public void setActivities(Record[] activities) {
		activityGrid.setData(activities);
		
	}

	@Override
	public void addActivity(Record activity) {
		activityGrid.addData(activity);
		
	}

	@Override
	public Record getTargetRecord() {
		Record[] target = targetGrid.getData();
		targetGrid.setData(new Record[1]);
		if(target.length > 0){
			return target[0];
		}
		return null;
	}

	@Override
	public void showNoMoreUnitsDialog() {
		box.show();
	}

	@Override
	public void showLoading() {
		//loading.startProcessing();
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "wait");
	}

	@Override
	public void hideLoading() {
		DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "default");
		//loading.stopProcessing();
		
	}

	@Override
	public void resest() {
		unitChoiceGrid.setData(new Record[4]);
		targetGrid.setData(new Record[1]);
	}
}
