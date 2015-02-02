package com.google.gwt.sample.mvpademo.client.activities.home;

import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.mvpademo.client.ClientFactory;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginPlace;
import com.google.gwt.sample.mvpademo.client.activities.register.RegisterPlace;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class HomeActivity extends MGWTAbstractActivity{
	private ClientFactory clientFactory;
	private Place place;
	public HomeActivity(ClientFactory clientFactory, Place place) {
		super();
		this.clientFactory = clientFactory;
		this.place = place;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		HomeView homeView = (HomeViewImpl)clientFactory.getHomeView();
		panel.setWidget(homeView);
		
		addHandlerRegistration(homeView.getLoginButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new LoginPlace());
			}
		}));
		addHandlerRegistration(homeView.getRegisterButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getPlaceController().goTo(new RegisterPlace());
			}
		}));
	}
}
