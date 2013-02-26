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
package com.unilorraine.projetdevie.client.ui.viewmodules.presentationmodule;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.unilorraine.projetdevie.client.ui.viewmodules.AbstractAppMenuModule;

/**
 * Activities are started and stopped by an ActivityManager associated with a container Widget.
 */
public class PreparationModuleActivity extends AbstractAppMenuModule implements PreparationModuleView.Presenter {

	private static final String MODULE_NAME = "Préparation du LP";
	private static final String MODULE_PIC_LINK = "";
	
	private static PreparationModuleView view;
	
	
	
	public PreparationModuleActivity() {
		super();
		setModuleName(MODULE_NAME);
	}

	@Override
	public void onStart() {
		if(view ==null)
			view = new PreparationModuleViewImpl();//(PreparationModuleView)GWT.create(PreparationModuleView.class);
		
		//This is very important, if you don't fire up the listener your module won't be shown
		super.onStart();
	};
	
	
	//TODO to implement
	@Override
	public void handleMenuAction(SelectionEvent<TreeItem> event) {
		System.out.println("Some event occured on " + event.getSelectedItem().getText());
	}

	@Override
	public Widget getWidget() {
		if(view != null)
			return view.asWidget();
		else{
			System.err.println("The PreparationModuleView is not instantiated!");
			return null;
		} 
	}
	
}
