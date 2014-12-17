package com.google.gwt.sample.mvpademo.server.service;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.google.gwt.sample.mvpademo.server.domain.Contact;
import com.google.gwt.sample.mvpademo.server.domain.Phone;
import com.google.gwt.sample.mvpademo.server.domain.User;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class PhoneDao {
	static {
		factory().register(Phone.class);
	}

	String addPhone(Phone phone, Contact contact) {
		if (ofy().load().type(Phone.class).parent(contact).id(phone.getPhone())
				.get() == null) {
			System.out.println("Success add phone: " + phone.getPhone());
			return ofy().save().entity(phone).now().getName();
		}
		System.out.println("Fail add phone: " + phone.getPhone());
		return "";
	}

	String updatePhone(Phone user) {
		System.out.print("Update contact to " + user.getPhone() + "\n");
		return ofy().save().entity(user).now().getName();
	}

	void delete(String phone, Contact contact) {
		ofy().delete().type(Phone.class).parent(contact).id(phone).now();
	}

	List<Phone> getPhonesByContact(Contact contact) {
		Contact retContact;
		if ((retContact = ofy().load().type(Contact.class)
				.id(contact.getName()).get()) == null) {
			System.out.println("Contact not found");
			return null;
		}
		List<Phone> phones = ofy().load().type(Phone.class)
				.ancestor(retContact).list();
		System.out.print(contact.getName() + ": " + phones.size() + " phones");
		return phones;
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}