package com.google.gwt.sample.mvpademo.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.sample.mvpademo.client.activities.home.HomeView;
import com.google.gwt.sample.mvpademo.client.activities.home.HomeViewImpl;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginView;
import com.google.gwt.sample.mvpademo.client.activities.login.LoginViewImpl;
import com.google.gwt.sample.mvpademo.client.activities.main.MainView;
import com.google.gwt.sample.mvpademo.client.activities.main.MainViewImpl;
import com.google.gwt.sample.mvpademo.client.activities.register.RegisterView;
import com.google.gwt.sample.mvpademo.client.activities.register.RegisterViewImpl;
import com.google.gwt.sample.mvpademo.client.rpc.LoginService;
import com.google.gwt.sample.mvpademo.client.rpc.LoginServiceAsync;
import com.google.gwt.sample.mvpademo.rpcobject.CSUser;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;

public class ClientFactoryImpl implements ClientFactory {
	public static String HOST = "http://contactsynchronize.appspot.com";
	public static String PATH = "/mvp_ap/";
	public static PhoneGap phoneGap = GWT.create(PhoneGap.class);
	private final SimpleEventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private final HomeView homeView = new HomeViewImpl();
	private final LoginView loginView = new LoginViewImpl();
	private final RegisterView registerView = new RegisterViewImpl();
	public static final MainView mainView = new MainViewImpl();
	public static final LoginServiceAsync loginService = GWT
			.create(LoginService.class);
	public static CSUser user = null;

	// private ChildBrowser childBrowser = GWT.create(ChildBrowser.class);

	public ClientFactoryImpl() {
		PhonegapUtil.prepareService((ServiceDefTarget) loginService, HOST
				+ PATH, "login");
		// childBrowser.initialize();
		// phoneGap.loadPlugin("childBrowser", childBrowser);
	}

	@Override
	public CSUser getUser() {
		return user;
	}

	@Override
	public void setUser(CSUser user) {
		this.user = user;
	}

	@Override
	public PhoneGap getPhoneGap() {
		return phoneGap;
	}

	@Override
	public EventBus getEventBus() {
		// TODO Auto-generated method stub
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		// TODO Auto-generated method stub
		return placeController;
	}

	@Override
	public HomeView getHomeView() {
		// TODO Auto-generated method stub
		return homeView;
	}

	@Override
	public LoginView getLoginView() {
		// TODO Auto-generated method stub
		return loginView;
	}

	@Override
	public RegisterView getRegisterView() {
		return registerView;
	}

	@Override
	public MainView getMainView() {
		return mainView;
	}

	@Override
	public LoginServiceAsync getLoginService() {
		return loginService;
	}

}
