package com.google.gwt.sample.mvpademo.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.sample.mvpademo.client.rpc.ClientService;
import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSPhone;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.sample.mvpademo.server.domain.Contact;
import com.google.gwt.sample.mvpademo.server.domain.Phone;
import com.google.gwt.sample.mvpademo.server.domain.User;
import com.google.gwt.sample.mvpademo.server.util.CSConverter;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

public class ClientServiceImpl extends RemoteServiceServlet implements
		ClientService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserDao dao = new UserDao();
	ContactDao contactDao = new ContactDao();
	PhoneDao phoneDao = new PhoneDao();

	@Override
	public CSUser login(CSUser csUser) {
		System.out.println("[loginService] Start");
		csUser.setVersion(0);
		User user = null;
		// if (dao.addUser(user).equals("")) {
		if ((user = dao.getByUserName(csUser.getUsername())) != null) {
			System.out.println("[login] Found " + csUser.getUsername());
			if (csUser.getPassword().equals(user.getPassword())) {
				System.out.println("[login] User name: " + user.getName()
						+ " Version:" + user.getVersion());
				return CSConverter.toCSUser(user);
			} else {
				System.out.println("[login]" + csUser.getUsername() +  "Password not correct");
				return null;
			}
		}
		return null;

		/*
		 * UserService userService = UserServiceFactory.getUserService(); User
		 * user = userService.getCurrentUser(); CSUser userClient = new
		 * CSUser();
		 * 
		 * if(user!=null){
		 * System.out.println("[loginService] User is already logged in");
		 * userClient.setLoggedIn(true);
		 * userClient.setEmailAddress(user.getEmail());
		 * userClient.setNickname(user.getNickname());
		 * userClient.setLogoutUrl(userService.createLogoutURL(requestUri)); }
		 * else { System.out.println("[loginService] User is not logged in");
		 * userClient.setLoggedIn(false);
		 * userClient.setLoginUrl(userService.createLoginURL(requestUri)); }
		 */

		// return userClient;
	}

	@Override
	public List<CSContact> pull(String name) {
		User user = dao.getByUserName(name);
		List<Contact> contacts;
		Contact contact;
		ArrayList<CSContact> csContacts = new ArrayList<CSContact>();
		CSContact csContact = null;
		List<Phone> phones;
		Phone phone;
		ArrayList<CSPhone> csPhones;
		CSPhone csPhone;
		if (user != null) {
			System.out.println("PULL\nUsername: " + user.getName());
			// /Get all contacts of user
			contacts = contactDao.getContactsByUser(user);
			// /Copy contact to cscontact object
			for (int i = 0; i < contacts.size(); i++) {
				contact = contacts.get(i);
				System.out.print(contact.getName() + " ");
				csContact = CSConverter.toCSContact(contact);
				// /Get all phones of contact
				phones = phoneDao.getPhonesByContact(contact);
				csPhones = new ArrayList<CSPhone>();
				// /Copy phone to csphone object
				for (int j = 0; j < phones.size(); j++) {
					phone = phones.get(j);
					System.out.print(phone.getPhone() + " ");
					csPhone = CSConverter.toCSPhone(phone);
					csPhones.add(csPhone);
				}
				System.out.println();
				csContact.setPhones(csPhones);
				csContacts.add(csContact);
			}
		}
		return csContacts;
	}

	@Override
	public Integer push(CSUser csUser) {
		System.out.println("PUSH\nUser Name: " + csUser.getUsername());
		ArrayList<CSContact> csContacts = (ArrayList<CSContact>) csUser
				.getContacts();
		ArrayList<CSPhone> csPhones;
		CSContact csContact;
		CSPhone csPhone;
		Contact contact;
		Phone phone;
		User user = dao.getByUserName(csUser.getUsername());
		if (user == null)
			return -1;

		for (int i = 0; i < csContacts.size(); i++) {
			csContact = csContacts.get(i);
			System.out.println(csContact.getStatus() + " "
					+ csContact.getName() + " ");
			contact = CSConverter.toContact(csContact);
			if (csContact.getStatus() == 1) {
				contact.setUser(Key.create(user));
				if (!contactDao.addContact(contact, user).equals("")) {
					csPhones = (ArrayList<CSPhone>) csContact.getPhones();
					for (int j = 0; j < csPhones.size(); j++) {
						csPhone = csPhones.get(j);
						// System.out.print(csPhone.getPhone() + " "
						// + csPhone.getStatus());
						phone = CSConverter.toPhone(csPhone);
						phone.setContact(Key.create(contact));
						phoneDao.addPhone(phone, contact);
					}
				}
			} else if (csContact.getStatus() == -1) {
				csPhones = (ArrayList<CSPhone>) csContact.getPhones();
				for (int j = 0; j < csPhones.size(); j++) {
					csPhone = csPhones.get(j);
					// System.out.print(csPhone.getPhone() + " "
					// + csPhone.getStatus());

					phoneDao.delete(csPhone.getPhone(), contact);
				}
				contactDao.delete(csContact.getName(), user);
			} else if (csContact.getStatus() == 2) {
				csPhones = (ArrayList<CSPhone>) csContact.getPhones();
				for (int j = 0; j < csPhones.size(); j++) {
					csPhone = csPhones.get(j);
					if (csPhone.getStatus() == 1) {
						contact.setUser(Key.create(user));
						phone = CSConverter.toPhone(csPhone);
						phone.setContact(Key.create(contact));
						phoneDao.addPhone(phone, contact);
					} else if (csPhone.getStatus() == -1) {
						System.out.println("Delete " + csPhone.getPhone());
						phoneDao.delete(csPhone.getPhone(), contact);
					}
				}
			}
			System.out.println();
		}

		user.setVersion(user.getVersion() + 1);
		dao.updateUser(user);
		return 0;
	}

	@Override
	public boolean register(CSUser csUser) {
		System.out.print("Register " + csUser.getUsername() + "\n");
		if (dao.addUser(CSConverter.toUser(csUser)) != "")
			return true;
		return false;
	}

	// @Override
	// public boolean register(User user) {
	// System.out.print("Register "+user.getName()+"\n");
	// if(dao.addUser(user)!="")
	// return true;
	// return false;
	// }
	//
	// @Override
	// public boolean checkUser(User user) {
	// User retUser;
	// if((retUser = dao.getByUserName(user.getName()))!=null){
	// if(retUser.getPassword().equals(user.getPassword()))
	// return true;
	// return false;
	// }
	// return false;
	// }
	//
	//
	// @Override
	// public boolean addContact(User user, List<Contact> contacts) {
	// User retUser;
	// System.out.print("Add contact of "+user.getName()+"\n");
	// System.out.print("Number of contacts: "+contacts.size()+"\n");
	// if((retUser = dao.getByUserName(user.getName()))!=null){
	// System.out.print("Found "+user.getName()+"\n");
	// for(int i = 0; i<contacts.size();i++){
	// contacts.get(i).setUser(Key.create(retUser));
	// if(contactDao.addContact(contacts.get(i),retUser)!=""){
	// retUser.addContact(Key.create(Key.create(retUser),Contact.class,
	// contacts.get(i).getName()));
	// if( dao.updateUser(retUser)!=""){
	// System.out.print("Update contact to user fail\n");
	// continue;
	// }
	// }
	// }
	// } else {
	// System.out.print("Cannot find "+user.getName()+"\n");
	// return false;
	// }
	// return true;
	// }
	//
	// @Override
	// public ArrayList<Contact> getContacts(User user) {
	// ArrayList<Contact> contacts = new ArrayList<Contact>();
	// List<Contact> retList;
	// System.out.print("Get contact of "+user.getName()+"\n");
	// retList = contactDao.getContactsByUser(user);
	// for(int i = 0; i< retList.size(); i++){
	// contacts.add(retList.get(i));
	// }
	// return contacts;
	// }
}