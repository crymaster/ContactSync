package com.google.gwt.sample.mvpademo.client.rpc;

import java.util.List;

import com.google.gwt.sample.mvpademo.rpcobject.CSContact;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClientServiceAsync {

	void login(CSUser user, AsyncCallback<CSUser> callback);

	void pull(String name, AsyncCallback<List<CSContact>> callback);

	void push(CSUser user, AsyncCallback<Integer> callback);

	void register(CSUser user, AsyncCallback<Boolean> callback);
	
//	void checkUser(User user, AsyncCallback<Boolean> callback);
//
//	void register(User user, AsyncCallback<Boolean> callback);
//
//	void addContact(User user, List<Contact> contacts,
//			AsyncCallback<Boolean> callback);
//
//	void getContacts(User user, AsyncCallback<ArrayList<Contact>> callback);

}
