package com.google.gwt.sample.mvpademo.client.activities.login;

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

public class LoginViewImpl implements LoginView{
	LayoutPanel main = new LayoutPanel();
	WidgetList widgetList = new WidgetList();
	HeaderPanel headerPanel = new HeaderPanel();
	Button login = new Button("Login");
	MTextBox userNameBox = new MTextBox();
	MPasswordTextBox passwordBox = new MPasswordTextBox();
	HeaderButton backButton = new HeaderButton();
	AlertDialog alert = new AlertDialog("", "");
	
	public LoginViewImpl() {
		headerPanel.setCenter("Login");
		backButton.setText("Back");
		headerPanel.setLeftWidget(backButton);
		main.add(headerPanel);
		userNameBox.setPlaceHolder("Enter username");
		passwordBox.setPlaceHolder("Enter password");
		widgetList.add(userNameBox);
		widgetList.add(passwordBox);
		widgetList.add(login);
		main.add(widgetList);
	}
	
	@Override
	public HasTapHandlers getLoginButton() {
		return login;
	}
	
	@Override
	public HasTapHandlers getBackButton() {
		return backButton;
	}
	
	@Override
	public MTextBox getUserNameBox() {
		return userNameBox;
	}
	
	@Override
	public MTextBox getPasswordBox() {
		return passwordBox;
	}
	
	@Override
	public void alert(String text) {
		alert.setText(text);
		alert.show();
	}
	
	@Override
	public void reset() {
		userNameBox.setText("");
		passwordBox.setText("");
		userNameBox.setPlaceHolder("Enter username");
		passwordBox.setPlaceHolder("Enter password");
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return main;
	}
}
