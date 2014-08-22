package com.google.gwt.sample.mvpademo.client.activities.register;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class RegisterPlace extends Place{
	String token = "register";
	
	public RegisterPlace() {
		super();
	}
	
	public String getToken() {
		return token;
	}
	
	public static class Tokenizer implements PlaceTokenizer<RegisterPlace>{
		@Override
		public RegisterPlace getPlace(String token) {
			return new RegisterPlace();
		}
		
		@Override
		public String getToken(RegisterPlace place) {
			return place.getToken();
		}
	}
}
