package com.google.gwt.sample.mvpademo.client.rpc;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.sample.mvpademo.domain.Contact;
import com.google.gwt.sample.mvpademo.domain.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService{
	boolean register(User user);
	boolean checkUser(User user);
	boolean addContact(User user, List<Contact> contacts);
	ArrayList<Contact> getContacts(User user);
}
