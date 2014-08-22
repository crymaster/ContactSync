package com.google.gwt.sample.mvpademo.client.activities.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class HomePlace extends Place{
	private String token = "home";
	public HomePlace() {
	}
	public String getToken() {
		return token;
	}
	
	public static class Tokenizer implements PlaceTokenizer<HomePlace>{
		@Override
		public HomePlace getPlace(String token) {
			return new HomePlace();
		}
		
		public String getToken(HomePlace place) {
			return place.getToken();
		};
	}
	
}
