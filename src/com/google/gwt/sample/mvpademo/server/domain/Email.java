package com.google.gwt.sample.mvpademo.server.domain;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Email {
	@Parent
	private Key<Contact> contact;
	@Id
	private long id;
	private String email;

	public Email() {
		// TODO Auto-generated constructor stub
	}

	public Key<Contact> getContact() {
		return contact;
	}

	public void setContact(Key<Contact> contact) {
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