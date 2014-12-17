package com.google.gwt.sample.mvpademo.client.rpc;

import java.util.List;

import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{
	CSUser login(CSUser user);
	List<CSContact> pull(String name);
	Integer push(CSUser user);
//	boolean register(User user);
//	boolean checkUser(User user);
//	boolean addContact(User user, List<Contact> contacts);
//	ArrayList<Contact> getContacts(User user);
}
