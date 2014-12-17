package com.google.gwt.sample.mvpademo.client.util;

import java.util.ArrayList;

import com.google.gwt.sample.mvpademo.rpcobject.CSContact;

public class FileContent {
	int version;
	ArrayList<CSContact> contacts;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public ArrayList<CSContact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<CSContact> contacts) {
		this.contacts = contacts;
	}
}
