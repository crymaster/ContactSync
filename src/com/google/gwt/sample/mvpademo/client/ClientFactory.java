package com.google.gwt.sample.mvpademo.client;

import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.sample.mvpademo.client.activities.home.HomeView;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginView;
import com.google.gwt.sample.mvpademo.client.activities.main.MainView;
import com.google.gwt.sample.mvpademo.client.activities.register.RegisterView;
import com.google.gwt.sample.mvpademo.client.rpc.UserServiceAsync;
import com.google.gwt.sample.mvpademo.domain.User;
import com.google.gwt.sample.mvpademo.shared.service.UserRequestFactory;
//import com.google.gwt.sample.mvpademo.shared.service.UserRequestFactory;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwtphonegap.client.PhoneGap;

public interface ClientFactory {
	EventBus getEventBus();
	PlaceController getPlaceController();
	HomeView getHomeView();
	LoginView getLoginView();
	RegisterView getRegisterView();
	MainView getMainView();
	UserServiceAsync getUserService();
	PhoneGap getPhoneGap();
	User getUser();
	void setUser(User user);
//	UserRequestFactory getRequestFactory();
}
