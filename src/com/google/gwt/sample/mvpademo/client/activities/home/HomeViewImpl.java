package com.google.gwt.sample.mvpademo.client.activities.home;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;

public class HomeViewImpl implements HomeView{
	private LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	private Button login = new Button("Log in");
	private Button register = new Button("Register"); 
	
	public HomeViewImpl() {
		main.setWidth("100%");
		headerPanel.setCenter("My first App");
		main.add(headerPanel);
		main.add(login);
		main.add(register);
	}
	
	@Override
	public HasTapHandlers getLoginButton() {
		return login;
	}
	
	@Override
	public HasTapHandlers getRegisterButton() {
		return register;
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return main;
	}
}
