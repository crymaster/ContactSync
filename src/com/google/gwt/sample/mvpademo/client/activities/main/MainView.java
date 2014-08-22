package com.google.gwt.sample.mvpademo.client.activities.main;

import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.MTextArea;

public interface MainView extends IsWidget{
	void alert(String text);
	HasTapHandlers getBackButton();
	public String getText();
	public void setText(String text);
}
