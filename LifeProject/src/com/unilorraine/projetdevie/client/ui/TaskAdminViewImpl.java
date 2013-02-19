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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.menu.MenuButton;
import com.unilorraine.projetdevie.client.ui.binder.TaskEditorBinder;
import com.unilorraine.projetdevie.client.ui.binder.TransitTaskEditor;

/**
 * Sample implementation of {@link TaskAdminView}.
 */
public class TaskAdminViewImpl extends FlowPanel implements TaskAdminView {
	private Presenter listener;
	private Label activityName;
	private Button saveTaskBtn;
	private TransitTaskEditor transitTaskEditor;
	private TileGrid tileGrid;

	public TaskAdminViewImpl() {
		
		HorizontalPanel titlePanel = new HorizontalPanel();
		add(titlePanel);
		titlePanel.setWidth("600px");
		
		Label activityTitle = new Label("Activit\u00E9");
		titlePanel.add(activityTitle);
		activityTitle.setWidth("76px");
		
		activityName = new Label("no Name");
		titlePanel.add(activityName);
		titlePanel.setCellHorizontalAlignment(activityName, HasHorizontalAlignment.ALIGN_CENTER);
		activityName.setWidth("261px");
		
		MenuButton mnbtnAction = new MenuButton("Actions");
		titlePanel.add(mnbtnAction);
		
		HorizontalPanel taskPanel = new HorizontalPanel();
		add(taskPanel);
		
		tileGrid = new TileGrid();
		tileGrid.setSize("377px", "220px");
		taskPanel.add(tileGrid);
		
		transitTaskEditor = new TransitTaskEditor();
		add(transitTaskEditor);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		add(horizontalPanel);
		
		saveTaskBtn = new Button("Sauver la t\u00E2che");
		horizontalPanel.add(saveTaskBtn);

	}

	@Override
	public void setName(String name) {
		//button.setHTML(name);
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}
