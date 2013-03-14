package com.unilorraine.projetdevie.client.shared.transitentities;

import java.io.Serializable;
/**
 * Login transit entity from the tutorial : https://developers.google.com/web-toolkit/doc/1.6/tutorial/appengine
 * @author Christophe
 *
 */
public class LoginInfo implements Serializable {

	  private boolean loggedIn = false;
	  private String loginUrl;
	  private String logoutUrl;
	  private String emailAddress;
	  private String nickname;

	  public boolean isLoggedIn() {
	    return loggedIn;
	  }

	  public void setLoggedIn(boolean loggedIn) {
	    this.loggedIn = loggedIn;
	  }

	  public String getLoginUrl() {
	    return loginUrl;
	  }

	  public void setLoginUrl(String loginUrl) {
	    this.loginUrl = loginUrl;
	  }

	  public String getLogoutUrl() {
	    return logoutUrl;
	  }

	  public void setLogoutUrl(String logoutUrl) {
	    this.logoutUrl = logoutUrl;
	  }

	  public String getEmailAddress() {
	    return emailAddress;
	  }

	  public void setEmailAddress(String emailAddress) {
	    this.emailAddress = emailAddress;
	  }

	  public String getNickname() {
	    return nickname;
	  }

	  public void setNickname(String nickname) {
	    this.nickname = nickname;
	  }
	}
