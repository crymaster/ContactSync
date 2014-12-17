package com.google.gwt.sample.mvpademo.server.domain;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Phone implements Serializable, IsSerializable {
	@Parent
	private Key<Contact> contact;
	@Id
	private String phone;

	public Phone() {
		// TODO Auto-generated constructor stub
	}

	public Key<Contact> getContact() {
		return contact;
	}

	public void setContact(Key<Contact> contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}