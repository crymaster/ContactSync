package com.google.gwt.sample.mvpademo.server.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.sample.mvpademo.client.rpc.UserService;
import com.google.gwt.sample.mvpademo.domain.Contact;
import com.google.gwt.sample.mvpademo.domain.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

public class UserServiceImpl extends RemoteServiceServlet implements UserService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UserDao dao = new UserDao();
	ContactDao contactDao = new ContactDao();
	@Override
	public boolean register(User user) {
		System.out.print("Register "+user.getName()+"\n");
		if(dao.addUser(user)!="")
			return true;
		return false;
	}
	
	@Override
	public boolean checkUser(User user) {
		User retUser;
		if((retUser = dao.getByUserName(user.getName()))!=null){
			if(retUser.getPassword().equals(user.getPassword()))
				return true;
			return false;
		}
		return false;
	}
	
	
	@Override
	public boolean addContact(User user, List<Contact> contacts) {
		User retUser;
		System.out.print("Add contact of "+user.getName()+"\n");
		System.out.print("Number of contacts: "+contacts.size()+"\n");
		if((retUser = dao.getByUserName(user.getName()))!=null){
			System.out.print("Found "+user.getName()+"\n");
			for(int i = 0; i<contacts.size();i++){
				contacts.get(i).setUser(Key.create(retUser));
				if(contactDao.addContact(contacts.get(i),retUser)!=""){
					retUser.addContact(Key.create(Key.create(retUser),Contact.class, contacts.get(i).getName()));
					if( dao.updateUser(retUser)!=""){
						System.out.print("Update contact to user fail\n");
						continue;
					}
				}
			}
		} else {
			System.out.print("Cannot find "+user.getName()+"\n");
			return false;
		}
		return true;
	}
	
	@Override
	public ArrayList<Contact> getContacts(User user) {
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		List<Contact> retList;
		System.out.print("Get contact of "+user.getName()+"\n");
		retList =  contactDao.getContactsByUser(user);
		for(int i = 0; i< retList.size(); i++){
			contacts.add(retList.get(i));
		}
		return contacts;
	}
}