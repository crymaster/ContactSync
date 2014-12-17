package com.google.gwt.sample.mvpademo.client.util;

import java.util.ArrayList;

import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSEmail;
import com.google.gwt.sample.mvpademo.rpcobject.CSPhone;
import com.googlecode.gwtphonegap.client.contacts.Contact;

public class CSConverter {
	public static CSContact toCSContact(Contact contact) {
		CSContact csContact = new CSContact();
		csContact.setDisplayName(contact.getDisplayName());
		csContact.setNickname(contact.getNickName());
		String name = "";
		if (contact.getName().getGivenName() != null)
			name = contact.getName().getGivenName() + " ";
		if (contact.getName().getMiddleName() != null)
			name += contact.getName().getMiddleName() + " ";
		if (contact.getName().getFamilyName() != null)
			name += contact.getName().getFamilyName();
		csContact.setName(name.trim());
		// csContact.setHonorificPrefix(contact.getName()
		// .getHonoricPrefix());
		// csContact.setHonorificSuffix(contact.getName()
		// .getHonoricSuffix());
		// csContact.setDob(contact.getBirthDay());
		CSPhone phone;
		for (int j = 0; j < contact.getPhoneNumbers().length(); j++) {
			phone = new CSPhone();
			phone.setPhone(contact.getPhoneNumbers().get(j).getValue());
			phone.setContact(csContact);
			csContact.getPhones().add(phone);
		}
		CSEmail email;
		for (int j = 0; j < contact.getEmails().length(); j++) {
			email = new CSEmail();
			email.setEmail(contact.getEmails().get(j).toString());
			email.setContact(csContact);
			csContact.getEmails().add(email);
		}
		return csContact;
	}

	public static String toFileContent(int version,
			ArrayList<CSContact> contacts) {
		String content = "";
		CSContact contact;
		ArrayList<CSPhone> phones;
		CSPhone phone;
		content += version + "\n";
		for(int i = 0; i< contacts.size(); i++){
			contact = contacts.get(i);
			content += contact.getName() + "\t" + contact.getDisplayName() + "\t" + contact.getNickname();
			phones = (ArrayList<CSPhone>)contact.getPhones();
			for(int j = 0; j< phones.size(); j++){
				phone = phones.get(j);
				content += "\t" + phone.getPhone();
			}
			content += "\n";
		}
		return content;
	}
}
