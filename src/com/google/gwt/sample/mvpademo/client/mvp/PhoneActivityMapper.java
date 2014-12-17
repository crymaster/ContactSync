package com.google.gwt.sample.mvpademo.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.mvpademo.client.ClientFactory;
import com.google.gwt.sample.mvpademo.client.activities.home.HomeActivity;
import com.google.gwt.sample.mvpademo.client.activities.home.HomePlace;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginActivity;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginPlace;
import com.google.gwt.sample.mvpademo.client.activities.main.MainActivity;
import com.google.gwt.sample.mvpademo.client.activities.main.MainPlace;
import com.google.gwt.sample.mvpademo.client.activities.register.RegisterActivity;
import com.google.gwt.sample.mvpademo.client.activities.register.RegisterPlace;

public class PhoneActivityMapper implements ActivityMapper{
	private ClientFactory clientFactory;

	public PhoneActivityMapper(ClientFactory clientFactory) {
		super();
		this.clientFactory = clientFactory;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if(place instanceof HomePlace){
			return new HomeActivity(clientFactory, place);
		} else if(place instanceof LoginPlace){
			return new LoginActivity(clientFactory, place);
		} else if(place instanceof RegisterPlace){
			return new RegisterActivity(clientFactory, place);
		} else if(place instanceof MainPlace){
				return new MainActivity(clientFactory, place);
		} else return null;
	}
}
