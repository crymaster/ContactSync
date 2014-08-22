package com.google.gwt.sample.mvpademo.domain;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

@Entity
public class Contact implements Serializable,IsSerializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Parent private Key<User> user;
	@Id private String name;
	String phone;
	
	public Contact() {
		// TODO Auto-generated constructor stub
	}
	
	public String getUser() {
		return user.getName();
	}
	public void setUser(Key<User> user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
