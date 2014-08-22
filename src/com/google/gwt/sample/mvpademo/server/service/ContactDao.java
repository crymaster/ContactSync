package com.google.gwt.sample.mvpademo.server.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.gwt.sample.mvpademo.domain.Contact;
import com.google.gwt.sample.mvpademo.domain.User;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class ContactDao{
	static {
		factory().register(Contact.class);
	}
	
	String addContact(Contact contact, User user){
		System.out.print("In addContact() of "+user.getName()+"and contact "+contact.getName()+"\n");
		if(ofy().load().type(Contact.class).parent(user).id(contact.getName()).get()==null){
			System.out.print("begin to add contact\n");
			return ofy().save().entity(contact).now().getName();
		}
		System.out.print("Add contact fail \n");
		return "";
	}
	
	List<Contact> getContactsByUser(User user){
		User retUser;
		if((retUser = ofy().load().type(User.class).id(user.getName()).get())==null){
			System.out.println("Found user");
			return null;
		}
		List<Contact> contacts = ofy().load().type(Contact.class).ancestor(retUser).list();
		System.out.print("Found "+contacts.size()+" contacts");
		return contacts;
	}
	
//	
//	User getByUserName(String userName){
//		return ofy().load().type(User.class).id(userName).get();
//	}
	
	public static ObjectifyFactory factory(){
		return ObjectifyService.factory();
	}
}
