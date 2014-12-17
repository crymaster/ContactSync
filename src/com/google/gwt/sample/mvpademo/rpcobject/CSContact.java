package com.google.gwt.sample.mvpademo.rpcobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSContact implements Serializable {
	private String id;
	private String displayName;
	private String nickname;
	private String name;
	private String honorificPrefix;
	private String honorificSuffix;
	private Date dob;
	private int status; // Normal: 0, new:1, update:2, delete: -1
	private List<CSPhone> phones;
	private List<CSEmail> emails;
	private CSUser user;

	public CSContact() {
		// TODO Auto-generated constructor stub
		status = 0;
		phones = new ArrayList<CSPhone>();
		emails = new ArrayList<CSEmail>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHonorificPrefix() {
		return honorificPrefix;
	}

	public void setHonorificPrefix(String honorificPrefix) {
		this.honorificPrefix = honorificPrefix;
	}

	public String getHonorificSuffix() {
		return honorificSuffix;
	}

	public void setHonorificSuffix(String honorificSuffix) {
		this.honorificSuffix = honorificSuffix;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public CSUser getUser() {
		return user;
	}

	public void setUser(CSUser user) {
		this.user = user;
	}

	public List<CSPhone> getPhones() {
		return phones;
	}

	public void setPhones(List<CSPhone> phones) {
		this.phones = phones;
	}

	public List<CSEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<CSEmail> emails) {
		this.emails = emails;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}