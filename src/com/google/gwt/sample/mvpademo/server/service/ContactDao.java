package com.google.gwt.sample.mvpademo.server.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.gwt.sample.mvpademo.server.domain.Contact;
import com.google.gwt.sample.mvpademo.server.domain.Phone;
import com.google.gwt.sample.mvpademo.server.domain.User;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class ContactDao {
	static {
		factory().register(Contact.class);
	}

	String addContact(Contact contact, User user) {
		if (ofy().load().type(Contact.class).parent(user).id(contact.getName())
				.get() == null) {
			System.out.println("Success add contact: " + contact.getName());
			return ofy().save().entity(contact).now().getName();
		}
		System.out.println("Fail add contact: " + contact.getName());
		return "";
	}
	
	Contact getContactByName(String name, User user){
		return ofy().load().type(Contact.class).parent(user).id(name).get();
	}
	
	List<Contact> getContactsByUser(User user) {
		User retUser;
		if ((retUser = ofy().load().type(User.class).id(user.getName()).get()) == null) {
			System.out.println("User not found");
			return null;
		}
		List<Contact> contacts = ofy().load().type(Contact.class)
				.ancestor(retUser).list();
		System.out.println("Found " + contacts.size() + " contacts");
		return contacts;
	}
	
	void delete(String name, User user){
		ofy().delete().type(Contact.class).parent(user).id(name).now();
	}

	//
	// User getByUserName(String userName){
	// return ofy().load().type(User.class).id(userName).get();
	// }

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}