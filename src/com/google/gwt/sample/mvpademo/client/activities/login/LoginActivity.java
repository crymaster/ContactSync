package com.google.gwt.sample.mvpademo.client.activities.login;

import java.util.ArrayList;

import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.mvpademo.client.ClientFactory;
import com.google.gwt.sample.mvpademo.client.activities.home.HomePlace;
import com.google.gwt.sample.mvpademo.client.activities.main.MainPlace;
import com.google.gwt.sample.mvpademo.domain.Contact;
import com.google.gwt.sample.mvpademo.domain.User;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class LoginActivity extends MGWTAbstractActivity{
	private ClientFactory clientFactory;
	private Place place;
	private User user;
	
	public LoginActivity(ClientFactory clientFactory, Place place) {
		super();
		this.clientFactory = clientFactory;
		this.place = place;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		LoginView loginView = clientFactory.getLoginView();
		panel.setWidget(loginView);
		addHandlerRegistration(loginView.getBackButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getLoginView().reset();
				clientFactory.getPlaceController().goTo(new HomePlace());
			}
		}));
		addHandlerRegistration(loginView.getLoginButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				if(!validateField()) return;
				
				String userName = clientFactory.getLoginView().getUserNameBox().getText();
 				String pass = clientFactory.getLoginView().getPasswordBox().getText();
				
				user = new User();
				user.setName(userName);
				user.setPassword(pass);
				clientFactory.getUserService().checkUser(user, new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						if(result){
							clientFactory.setUser(user);
							clientFactory.getPlaceController().goTo(new MainPlace());
						}
						else
							clientFactory.getLoginView().alert("Incorrect username or password");
					}
					
					@Override
					public void onFailure(Throwable caught) {
						clientFactory.getLoginView().alert("Log in failed");
					}
				});
			}
			
			boolean validateField(){
				MTextBox userName = clientFactory.getLoginView().getUserNameBox();
				MTextBox pass = clientFactory.getLoginView().getPasswordBox();
				boolean validate = true;
				
				if(userName.getText().isEmpty()){
					userName.setPlaceHolder("Please enter username");
					validate = false;
				}
				if(pass.getText().isEmpty()){
					pass.setPlaceHolder("Please enter password");
					validate = false;
				}
				return validate;
			}
		}));
	}
}