package com.google.gwt.sample.mvpademo.rpcobject;

import java.io.Serializable;

public class CSPhone implements Serializable {
	private long id;
	private String phone;
	private int status; // normal: 0, new: 1, delete: -1
	private CSContact contact;

	public CSPhone() {
		status = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CSContact getContact() {
		return contact;
	}

	public void setContact(CSContact contact) {
		this.contact = contact;
	}

}
