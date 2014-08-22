package com.google.gwt.sample.mvpademo.client.activities.main;

import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.dialog.AlertDialog;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MTextArea;

public class MainViewImpl implements MainView{
	private LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	private HeaderButton backButton = new HeaderButton();
	private AlertDialog alert = new AlertDialog("", "");
	private MTextArea text = new MTextArea();
	
	public MainViewImpl() {
		main.setWidth("100%");
		backButton.setText("Back");
		headerPanel.setCenter("Contact List");
		headerPanel.setLeftWidget(backButton);
		main.add(headerPanel);
		main.add(text);
	}
	
	public void alert(String text){
		alert.setText(text);
		alert.show();
	}
	
	@Override
	public HasTapHandlers getBackButton() {
		return backButton;
	}
	
	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return main;
	}

	public String getText() {
		return text.getText();
	}

	public void setText(String text) {
		this.text.setText(text);
	}
}
