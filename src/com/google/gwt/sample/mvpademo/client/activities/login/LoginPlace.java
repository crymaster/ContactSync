package com.google.gwt.sample.mvpademo.client.activities.login;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class LoginPlace extends Place{
	private String token = "login";

	public LoginPlace() {
		super();
	}

	public String getToken() {
		return token;
	}
	
	public static class Tokenizer implements PlaceTokenizer<LoginPlace>{
		@Override
		public LoginPlace getPlace(String token) {
			return new LoginPlace();
		}
		
		@Override
		public String getToken(LoginPlace place) {
			return place.getToken();
		}
	}
}
