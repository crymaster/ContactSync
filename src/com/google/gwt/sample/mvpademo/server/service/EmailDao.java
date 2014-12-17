package com.google.gwt.sample.mvpademo.server.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.google.gwt.sample.mvpademo.server.domain.Email;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class EmailDao {
	static {
		factory().register(Email.class); 
	}

	Long addEmail(Email email) {
		return ofy().save().entity(email).now().getId();
	}

	Long updatePhone(Email email) {
		//System.out.print("Update contact to " + user.getEmail() + "\n");
		return ofy().save().entity(email).now().getId();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
