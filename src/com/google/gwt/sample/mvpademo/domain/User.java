package com.google.gwt.sample.mvpademo.domain;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class User implements Serializable{
	@Id private String name;
	private String password;
	private List<Key<Contact>> contacts = new ArrayList<Key<Contact>>();
	private static final long serialVersionUID = 1L;
	
	public User() {
	
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addContact(Key<Contact> contact){
		contacts.add(contact);
	}
	
	public void deleteContact(Key<Contact> contact){
		contacts.remove(contact);
	}
	
	public List<Key<Contact>> getContacts(){
		return contacts;
	}
}