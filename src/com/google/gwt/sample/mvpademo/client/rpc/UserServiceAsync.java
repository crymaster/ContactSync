package com.google.gwt.sample.mvpademo.client.rpc;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.sample.mvpademo.domain.Contact;
import com.google.gwt.sample.mvpademo.domain.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void checkUser(User user, AsyncCallback<Boolean> callback);

	void register(User user, AsyncCallback<Boolean> callback);

	void addContact(User user, List<Contact> contacts,
			AsyncCallback<Boolean> callback);

	void getContacts(User user, AsyncCallback<ArrayList<Contact>> callback);

}
