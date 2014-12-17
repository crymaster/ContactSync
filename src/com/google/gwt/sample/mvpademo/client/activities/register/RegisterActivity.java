package com.google.gwt.sample.mvpademo.client.activities.register;

import com.google.gwt.place.shared.Place;
import com.google.gwt.sample.mvpademo.client.ClientFactory;
import com.google.gwt.sample.mvpademo.client.activities.home.HomePlace;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class RegisterActivity extends MGWTAbstractActivity{
	private ClientFactory clientFactory;
	private Place place;
	
	public RegisterActivity(ClientFactory clientFactory, Place place) {
		super();
		this.clientFactory = clientFactory;
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		RegisterView registerView = clientFactory.getRegisterView();
		panel.setWidget(registerView);
		addHandlerRegistration(registerView.getBackButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				clientFactory.getRegisterView().reset();
				clientFactory.getPlaceController().goTo(new HomePlace());
			}
		}));
		addHandlerRegistration(registerView.getRegisterButton().addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				if (!validateFields()) return;
				String userName = clientFactory.getRegisterView().getUserNameBox().getText();
				String pass = clientFactory.getRegisterView().getPasswordBox().getText();
				
//				User user = new User();
//				user.setName(userName);
//				user.setPassword(pass);
//				clientFactory.getUserService().register(user, new AsyncCallback<Boolean>() {
//					
//					@Override
//					public void onSuccess(Boolean result) {
//						if(result)
//							clientFactory.getRegisterView().alert("Register successfully");
//						else 
//							clientFactory.getRegisterView().alert("This username already existed");
//					}
//					
//					@Override
//					public void onFailure(Throwable caught) {
//						clientFactory.getRegisterView().alert("Register failed");
//					}
//				});
			}
			
			private boolean validateFields(){
				MTextBox userName = clientFactory.getRegisterView().getUserNameBox();
				MTextBox pass = clientFactory.getRegisterView().getPasswordBox();
				MTextBox passConfirm = clientFactory.getRegisterView().getPasswordConfirmBox();
				boolean validate = true;
				if(userName.getText().isEmpty()){
					userName.setPlaceHolder("Please enter your username");
					validate = false;
				}
				
				if(pass.getText().isEmpty()){
					pass.setPlaceHolder("Please enter your password");
					validate = false;
				}
				
				if(passConfirm.getText().isEmpty()){
					passConfirm.setPlaceHolder("Please confirm your password");
					validate = false;
				}
				
				if(!pass.getText().isEmpty() && !passConfirm.getText().isEmpty() && !pass.getText().equals(passConfirm.getText())){
					validate = false;
					pass.setText("");
					passConfirm.setText("");
					pass.setPlaceHolder("Your password does not match");
					passConfirm.setPlaceHolder("Your password does not match");
				}
				
				return validate;
			}
		}));
	}
}
