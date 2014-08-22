package com.google.gwt.sample.mvpademo.client.mvp;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.google.gwt.sample.mvpademo.client.activities.home.HomePlace;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginPlace;
import com.google.gwt.sample.mvpademo.client.activities.main.MainPlace;
import com.google.gwt.sample.mvpademo.client.activities.register.RegisterPlace;

@WithTokenizers({HomePlace.Tokenizer.class,LoginPlace.Tokenizer.class,RegisterPlace.Tokenizer.class,MainPlace.Tokenizer.class})
public interface PhonePlaceHistoryMapper extends PlaceHistoryMapper{
	
}
