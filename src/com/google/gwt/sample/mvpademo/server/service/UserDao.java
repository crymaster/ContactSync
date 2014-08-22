package com.google.gwt.sample.mvpademo.server.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.gwt.sample.mvpademo.domain.User;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class UserDao{
	static {
		factory().register(User.class);
	}
	
	String addUser(User user){
		if(ofy().load().type(User.class).id(user.getName()).get()==null)
			return ofy().save().entity(user).now().getName();
		return "";
	}
	
	String updateUser(User user){
		System.out.print("Update contact to "+user.getName()+"\n");
		return ofy().save().entity(user).now().getName();
	}
	
	User getByUserName(String userName){
		return ofy().load().type(User.class).id(userName).get();
	}
	
	public static ObjectifyFactory factory(){
		return ObjectifyService.factory();
	}
}
