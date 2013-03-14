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
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.unilorraine.projetdevie.client.shared.transitentities.LoginInfo;
import com.unilorraine.projetdevie.client.ui.ApplicationPanelView.Presenter;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.i18n.client.HasDirection.Direction;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.smartgwt.client.widgets.ImgButton;

/**
 * Implementation for a {@link ApplicationPanelView}. It proposes a FlowPanel ion the right side and a left menu tree on the left side for the modules to use.
 * For more informations see {@link ApplicationPanelView} 
 */
public class ApplicationPanelViewImpl extends FlowPanel implements ApplicationPanelView {
	
	private static final String DISCONNECT_MES = ",  deconnectez-vous";

	private Presenter listener;
	private FlowPanel pluginPanel;
	private Tree menutTree;
	private Button btnMenu;
	private ImgButton imgButton;
	private Label menuTitle;
	
	private LoginDialog login;
	
	private PopupLoadingView popup;
	
	private VerticalPanel verticalPanel;
	private VerticalPanel logPanel;
	
	private Label nameLabel;
	private Anchor logoutAnchor = new Anchor();

	public ApplicationPanelViewImpl() {
		setSize("800px", "800px");
		
		verticalPanel = new VerticalPanel();
		
		verticalPanel.setSize("800px", "800px");
		
		HorizontalPanel logoutPanel = new HorizontalPanel();
		logoutPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		nameLabel = new Label();
		//nameLabel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		logoutPanel.add(nameLabel);
		//logoutPanel.setVisible(false);
		logoutPanel.add(logoutAnchor);
		logoutAnchor.setWidth("118px");
		
		
		logPanel = new VerticalPanel();
		logPanel.setSize("750px", "25px");
		logPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		logPanel.add(logoutPanel);
		
		verticalPanel.add(logPanel);
		verticalPanel.setWidth("776px");
		
		btnMenu = new Button("Dashboard");
		btnMenu.setWidth("75px");
		
		btnMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				listener.connectModuleHandler();
			}
		});
		verticalPanel.add(btnMenu);
		
		
		DockLayoutPanel mainPluginPanel = new DockLayoutPanel(Unit.EM);
		verticalPanel.add(mainPluginPanel);
		mainPluginPanel.setSize("100%", "800px");
		
		FlowPanel pluginFlowPanel = new FlowPanel();
		mainPluginPanel.addWest(pluginFlowPanel, 14.2);
		
		menuTitle = new Label("Menu");
		menuTitle.setStyleName("subtitle");
		pluginFlowPanel.add(menuTitle);
		menuTitle.setHeight("10px");
		menuTitle.setVisible(false);
		
		menutTree = new Tree();
		pluginFlowPanel.add(menutTree);
		menutTree.setWidth("127px");
		
		pluginPanel = new FlowPanel();
		mainPluginPanel.add(pluginPanel);
		pluginPanel.setSize("", "100%");
		
		popup = new PopupLoadingView();
	}
	
	@Override
	public void createView(){
		add(verticalPanel);
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
		//login.setPresenter(this.listener);

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
		if(treeItems.size() > 0)
			menuTitle.setVisible(true);
		
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
		menuTitle.setVisible(false);
		
	}

	@Override
	public void setSelectedItem(TreeItem item, boolean fireEvents) {
		menutTree.setSelectedItem(item, fireEvents);
	}

	@Override
	public void waitingPopup(boolean waiting) {
		if(waiting)
			popup.startProcessing();
		else
			popup.stopProcessing();
		
	}

	@Override
	public boolean isWaitingPopup() {
		return popup.isShowing();
	}

	@Override
	public void waitingMouse(boolean waiting) {
		if(waiting){
			DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "wait");
		}else
			DOM.setStyleAttribute(RootPanel.getBodyElement(), "cursor", "default");
		
	}

	@Override
	public boolean isWaitingMouse() {
		return DOM.getStyleAttribute(RootPanel.getBodyElement(), "cursor").equals("wait");
	}

	@Override
	public void showLogin(String loginUrl) {
		login = new LoginDialog(loginUrl);
		System.out.println("Show dialog");
		login.show();
	}

	@Override
	public void hideLogin() {
		login.hide();
	}

	@Override
	public void setLogOut(LoginInfo info) {
		nameLabel.setText(info.getEmailAddress());
		logoutAnchor.setText(DISCONNECT_MES);
		logoutAnchor.setHref(info.getLogoutUrl());
	}
	
}
