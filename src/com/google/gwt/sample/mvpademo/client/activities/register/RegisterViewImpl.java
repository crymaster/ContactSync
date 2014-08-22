package com.google.gwt.sample.mvpademo.client.activities.register;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.dialog.AlertDialog;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class RegisterViewImpl implements RegisterView{
	LayoutPanel main = new LayoutPanel();
	WidgetList widgetList = new WidgetList();
	HeaderPanel headerPanel = new HeaderPanel();
	Button register = new Button("Register");
	MTextBox userNameBox = new MTextBox();
	MPasswordTextBox passwordBox = new MPasswordTextBox();
	MPasswordTextBox passwordConfirmBox = new MPasswordTextBox();
	HeaderButton backButton = new HeaderButton();
	AlertDialog alert = new AlertDialog("", "");
	
	public RegisterViewImpl() {
		headerPanel.setCenter("Register");
		backButton.setText("Back");
		headerPanel.setLeftWidget(backButton);
		main.add(headerPanel);
		userNameBox.setPlaceHolder("Enter username");
		passwordBox.setPlaceHolder("Enter password");
		passwordConfirmBox.setPlaceHolder("Enter password confirmation");
		widgetList.add(userNameBox);
		widgetList.add(passwordBox);
		widgetList.add(passwordConfirmBox);
		widgetList.add(register);
		main.add(widgetList);
	}
	
	@Override
	public HasTapHandlers getRegisterButton() {
		return register;
	}
	
	@Override
	public HasTapHandlers getBackButton() {
		return backButton;
	}
	
	@Override
	public MTextBox getUserNameBox() {
		// TODO Auto-generated method stub
		return userNameBox;
	}
	
	@Override
	public MTextBox getPasswordBox() {
		// TODO Auto-generated method stub
		return passwordBox;
	}
	
	@Override
	public MTextBox getPasswordConfirmBox() {
		// TODO Auto-generated method stub
		return passwordConfirmBox;
	}
	
	@Override
	public void reset() {
		userNameBox.setPlaceHolder("Enter username");
		passwordBox.setPlaceHolder("Enter password");
		passwordConfirmBox.setPlaceHolder("Enter password confirmation");
	}
	
	@Override
	public void alert(String text) {
		alert.setText(text);
		alert.show();
	}
	
	@Override
	public Widget asWidget() {
		return main;
	}
}
