package com.google.gwt.sample.mvpademo.rpcobject;

public class CSEmail {
	private CSContact contact;
	private long id;
	private String email;

	public CSContact getContact() {
		return contact;
	}

	public void setContact(CSContact contact) {
		this.contact = contact;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}