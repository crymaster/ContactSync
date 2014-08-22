package com.google.gwt.sample.mvpademo.client.activities.home;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;

public interface HomeView extends IsWidget{
	HasTapHandlers getLoginButton();
	HasTapHandlers getRegisterButton();
}
