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
package com.unilorraine.projetdevie.client.ui;

import javax.swing.text.AsyncBoxView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.widgets.layout.FlowLayout;
import com.smartgwt.client.widgets.ImgButton;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.IButton;
import com.unilorraine.projetdevie.client.service.ActivityService;
import com.unilorraine.projetdevie.client.service.ActivityServiceAsync;
import com.unilorraine.projetdevie.client.shared.transitentities.TransitLPActivity;

/**
 * Sample implementation of {@link ActorHolderView}.
 */
public class ActorHolderViewImpl extends FlowPanel implements ActorHolderView {
	private Presenter listener;
	private ImgButton imgButton;

	public ActorHolderViewImpl() {
		
		DockPanel dockPanel = new DockPanel();
		dockPanel.setStyleName("body");
		add(dockPanel);
		dockPanel.setSize("790px", "525px");
		
		VerticalPanel horizontalPanel = new VerticalPanel();
		dockPanel.add(horizontalPanel, DockPanel.NORTH);
		dockPanel.setCellHorizontalAlignment(horizontalPanel, HasHorizontalAlignment.ALIGN_CENTER);
		dockPanel.setCellVerticalAlignment(horizontalPanel, HasVerticalAlignment.ALIGN_BOTTOM);
		horizontalPanel.setSize("495px", "143px");
		
		Button btnNewButton = new Button("New button");
		btnNewButton.setText("Changer");
		horizontalPanel.add(btnNewButton);
		horizontalPanel.setCellVerticalAlignment(btnNewButton, HasVerticalAlignment.ALIGN_MIDDLE);
		horizontalPanel.setCellHorizontalAlignment(btnNewButton, HasHorizontalAlignment.ALIGN_CENTER);
		
		ImgButton imgButton_2 = new ImgButton();
		horizontalPanel.add(imgButton_2);
		horizontalPanel.setCellHorizontalAlignment(imgButton_2, HasHorizontalAlignment.ALIGN_CENTER);
		
		VerticalPanel verticalPanel = new VerticalPanel();
		dockPanel.add(verticalPanel, DockPanel.WEST);
		verticalPanel.setHeight("194px");
		dockPanel.setCellHorizontalAlignment(verticalPanel, HasHorizontalAlignment.ALIGN_CENTER);
		
		imgButton = new ImgButton();
		imgButton.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
			public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
				ActivityServiceAsync service = GWT.create(ActivityService.class);
				
				AsyncCallback<TransitLPActivity> callback = new AsyncCallback<TransitLPActivity>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println(caught.getMessage());
						
					}

					@Override
					public void onSuccess(TransitLPActivity result) {
						if (result == null)
							System.out.println("Error server!");
						else
							System.out.println("Result : " + result.getId());
						
					}
					
				};
				
				service.createEntity(callback);
				
			}
		});
		verticalPanel.add(imgButton);
		verticalPanel.setCellVerticalAlignment(imgButton, HasVerticalAlignment.ALIGN_BOTTOM);
		
		Label lblNewLabel = new Label("Preparation");
		verticalPanel.add(lblNewLabel);
		verticalPanel.setCellHorizontalAlignment(lblNewLabel, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setCellVerticalAlignment(lblNewLabel, HasVerticalAlignment.ALIGN_MIDDLE);
		
		VerticalPanel verticalPanel_1 = new VerticalPanel();
		dockPanel.add(verticalPanel_1, DockPanel.EAST);
		verticalPanel_1.setHeight("186px");
		dockPanel.setCellHorizontalAlignment(verticalPanel_1, HasHorizontalAlignment.ALIGN_CENTER);
		
		ImgButton imgButton_1 = new ImgButton();
		verticalPanel_1.add(imgButton_1);
		verticalPanel_1.setCellVerticalAlignment(imgButton_1, HasVerticalAlignment.ALIGN_BOTTOM);
		
		Label lblNewLabel_1 = new Label("Creation");
		verticalPanel_1.add(lblNewLabel_1);
		verticalPanel_1.setCellHorizontalAlignment(lblNewLabel_1, HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel_1.setCellVerticalAlignment(lblNewLabel_1, HasVerticalAlignment.ALIGN_MIDDLE);

	}


	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}


	@Override
	public void setName(String helloName) {
		// TODO Auto-generated method stub
		
	}

}
