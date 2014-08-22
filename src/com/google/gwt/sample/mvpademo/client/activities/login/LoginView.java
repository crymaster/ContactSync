package com.google.gwt.sample.mvpademo.client.activities.login;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public interface LoginView extends IsWidget{
	HasTapHandlers getLoginButton();
	HasTapHandlers getBackButton();
	MTextBox getUserNameBox();
	MTextBox getPasswordBox();
	void alert(String text);
	void reset();
}
