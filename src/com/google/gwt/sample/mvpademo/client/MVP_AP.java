package com.google.gwt.sample.mvpademo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.sample.mvpademo.client.activities.main.MainPlace;
import com.google.gwt.sample.mvpademo.client.mvp.PhoneActivityMapper;
import com.google.gwt.sample.mvpademo.client.mvp.PhoneAnimationMapper;
import com.google.gwt.sample.mvpademo.client.mvp.PhonePlaceHistoryMapper;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.OsDetection;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MVP_AP implements EntryPoint {
	public static ClientFactoryImpl clientFactory = GWT.create(ClientFactoryImpl.class);
	public static PhoneActivityMapper appActivityMapper;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//Window.alert("onModuleLoaded: " + getOSName());
		clientFactory.getPhoneGap().addHandler(new PhoneGapAvailableHandler() {
			
			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				// TODO Auto-generated method stub
				startApp();
			}
		});
		clientFactory.getPhoneGap().addHandler(new PhoneGapTimeoutHandler() {
			
			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		clientFactory.getPhoneGap().initializePhoneGap();
		//startApp();
	}
	
	private void startApp() {
		PhonePlaceHistoryMapper historyMapper = GWT.create(PhonePlaceHistoryMapper.class);
		final PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		createPhoneDisplay(clientFactory);
		historyHandler.register(clientFactory.getPlaceController(), clientFactory.getEventBus(), new MainPlace());				
		historyHandler.handleCurrentHistory();
	}
	
	private void createPhoneDisplay(ClientFactoryImpl clientFactory) {
		AnimatableDisplay display = GWT.create(AnimatableDisplay.class);
		appActivityMapper = new PhoneActivityMapper(clientFactory);
		PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();
		AnimatingActivityManager activityManager = new AnimatingActivityManager(appActivityMapper, 
				appAnimationMapper, clientFactory.getEventBus());
		activityManager.setDisplay(display);
		RootPanel.get().add(display);
	}
	
	private String getOSName() {
		OsDetection os = MGWT.getOsDetection();
		String name = "";
		if (os.isIPadRetina())
			name += " IpadRetina ";
		if (os.isIPad())
			name += " Ipad ";
		if (os.isAndroid())
			name += " Android ";
		if (os.isAndroidPhone())
			name += " AndroidPhone ";
		if (os.isAndroidTablet())
			name += " AndroidTablet ";
		if (os.isIOs())
			name += " ios ";
		if (os.isIPhone())
			name += " Iphone ";
		if (os.isPhone())
			name += " phone ";
		if (os.isRetina())
			name += " Retina ";
		if (os.isTablet())
			name += " Tablet ";
		return name;
	}
	
}
