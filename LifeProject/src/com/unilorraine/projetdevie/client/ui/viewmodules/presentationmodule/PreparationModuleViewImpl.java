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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Sample implementation of {@link PreparationModuleView}.
 */
public class PreparationModuleViewImpl extends FlowPanel implements PreparationModuleView {
	
	private Presenter listener;

	public PreparationModuleViewImpl() {
		
		Label lblHelloNewModule = new Label("Hello New Module");
		lblHelloNewModule.setStyleName("title");
		add(lblHelloNewModule);
		
		TextBox textBox = new TextBox();
		add(textBox);


	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

}
