package com.google.gwt.sample.mvpademo.client.activities.register;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public interface RegisterView extends IsWidget{
	HasTapHandlers getBackButton();
	HasTapHandlers getRegisterButton();
	MTextBox getUserNameBox();
	MTextBox getPasswordBox();
	MTextBox getPasswordConfirmBox();
	void alert(String text);
	void reset();
}
