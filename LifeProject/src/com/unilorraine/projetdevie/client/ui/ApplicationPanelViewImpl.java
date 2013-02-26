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

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.unilorraine.projetdevie.client.ui.ApplicationPanelView.Presenter;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;

/**
 * Implementation for a {@link ApplicationPanelView}. It proposes a FlowPanel ion the right side and a left menu tree on the left side for the modules to use.
 * For more informations see {@link ApplicationPanelView} 
 */
public class ApplicationPanelViewImpl extends FlowPanel implements ApplicationPanelView {
	
	private Presenter listener;
	private VerticalPanel pulginMenu;
	private FlowPanel pluginPanel;
	private Tree menutTree;
	private Button btnMenu;

	public ApplicationPanelViewImpl() {
		setSize("800px", "500px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		add(verticalPanel);
		verticalPanel.setSize("800px", "500px");
		
		Label loginInfo = new Label("Christophe logout");
		loginInfo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		verticalPanel.add(loginInfo);
		loginInfo.setWidth("776px");
		
		btnMenu = new Button("Menu");
		btnMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				listener.gotoDefaultMenu();
			}
		});
		verticalPanel.add(btnMenu);
		btnMenu.setWidth("75px");
		
		DockLayoutPanel mainPluginPanel = new DockLayoutPanel(Unit.EM);
		verticalPanel.add(mainPluginPanel);
		mainPluginPanel.setSize("100%", "400px");
		
		pulginMenu = new VerticalPanel();
		mainPluginPanel.addWest(pulginMenu, 15.0);
		pulginMenu.setSize("150px", "100%");
		pulginMenu.setSpacing(15);
		
		menutTree = new Tree();
		pulginMenu.add(menutTree);
		menutTree.setWidth("127px");
		
		pluginPanel = new FlowPanel();
		mainPluginPanel.add(pluginPanel);
		pluginPanel.setSize("", "100%");

	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	@Override
	public void setAppModuleView(Widget widget) {
		if(pluginPanel.getWidgetCount() > 0)
		pluginPanel.remove(0);
		pluginPanel.add(widget);
		
	}

	@Override
	public void setAppMenuItems(List<TreeItem> treeItems) {
		menutTree.removeItems();
		for(TreeItem item : treeItems){
			item.addStyleName("treeItem");
			menutTree.addItem(item);
		}
		
	}

	@Override
	public void emptyModule() {
		pluginPanel.remove(0);
		menutTree.removeItems();
	}

	@Override
	public void setMenuTreeListener(SelectionHandler<TreeItem> listener) {
		menutTree.addSelectionHandler(listener);
		
	}

	@Override
	public void emptyMenu() {
		menutTree.removeItems();
		
	}

}
