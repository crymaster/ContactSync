package com.google.gwt.sample.mvpademo.client.activities.main;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class MainPlace extends Place{
	private String token = "main";
	public MainPlace() {
	}
	public String getToken() {
		return token;
	}
	
	public static class Tokenizer implements PlaceTokenizer<MainPlace>{
		@Override
		public MainPlace getPlace(String token) {
			return new MainPlace();
		}
		
		public String getToken(MainPlace place) {
			return place.getToken();
		};
	}
	
}
