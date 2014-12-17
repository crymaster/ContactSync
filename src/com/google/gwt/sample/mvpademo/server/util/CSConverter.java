package com.google.gwt.sample.mvpademo.server.util;

import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSPhone;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.sample.mvpademo.server.domain.Contact;
import com.google.gwt.sample.mvpademo.server.domain.Phone;
import com.google.gwt.sample.mvpademo.server.domain.User;

public class CSConverter {
	public static User toUser(CSUser csUser) {
		User user = new User();
		user.setName(csUser.getEmailAddress());
		user.setVersion(csUser.getVersion());
		return user;
	}

	public static CSUser toCSUser(User user) {
		CSUser csUser = new CSUser();
		csUser.setEmailAddress(user.getName());
		csUser.setVersion(user.getVersion());
		return csUser;
	}
	
	public static Contact toContact(CSContact csContact){
		Contact contact = new Contact();
		contact.setName(csContact.getName());
		contact.setDob(csContact.getDob());
		return contact;
	}
	
	public static CSContact toCSContact(Contact contact){
		CSContact csContact = new CSContact();
		csContact.setName(contact.getName());
		csContact.setDob(contact.getDob());
		return csContact;
	}
	
	public static Phone toPhone(CSPhone csPhone){
		Phone phone = new Phone();
		phone.setPhone(csPhone.getPhone());
		return phone;
	}
	
	public static CSPhone toCSPhone(Phone phone){
		CSPhone csPhone = new CSPhone();
		csPhone.setPhone(phone.getPhone());
		return csPhone;
	}
}
