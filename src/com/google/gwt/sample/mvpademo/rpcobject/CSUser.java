package com.google.gwt.sample.mvpademo.rpcobject;

import java.io.Serializable;
import java.util.List;

public class CSUser implements Serializable {
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String username;
	private String nickname;
	private String password;
	private int version;
	private List<CSContact> contacts;

	public CSUser() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<CSContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<CSContact> contacts) {
		this.contacts = contacts;
	}

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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
