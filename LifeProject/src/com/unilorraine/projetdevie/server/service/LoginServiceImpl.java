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
package com.unilorraine.projetdevie.server.service;

import com.unilorraine.projetdevie.client.service.LoginService;
import com.unilorraine.projetdevie.client.shared.transitentities.LoginInfo;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	
	 public LoginInfo login(String requestUri) {
		    UserService userService = UserServiceFactory.getUserService();
		    User user = userService.getCurrentUser();
		    LoginInfo loginInfo = new LoginInfo();

		    if (user != null) {
		      loginInfo.setLoggedIn(true);
		      loginInfo.setEmailAddress(user.getEmail());
		      loginInfo.setNickname(user.getNickname());
		      loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		    } else {
		      loginInfo.setLoggedIn(false);
		      loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		    }
		    return loginInfo;
		  }
}
