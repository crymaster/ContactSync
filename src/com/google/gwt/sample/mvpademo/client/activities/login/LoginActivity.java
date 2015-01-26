package com.google.gwt.sample.mvpademo.client.activities.login;

import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.mvpademo.client.ClientFactory;
import com.google.gwt.sample.mvpademo.client.activities.home.HomePlace;
import com.google.gwt.sample.mvpademo.client.activities.main.MainPlace;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class LoginActivity extends MGWTAbstractActivity{
	private ClientFactory clientFactory;
	private Place place;
	private CSUser user;
	
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
				
				user = new CSUser();
				user.setUsername(userName);
				user.setPassword(pass);
				clientFactory.getClientService().login(user, new AsyncCallback<CSUser>() {
					
					@Override
					public void onSuccess(CSUser retUser) {
						if(retUser != null){
							clientFactory.setUser(retUser);
							clientFactory.getPlaceController().goTo(new MainPlace());
//							clientFactory.getRegisterView().alert("Login successfully");
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