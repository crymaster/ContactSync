package com.google.gwt.sample.mvpademo.client.activities.main;

import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.dialog.AlertDialog;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.CellGroup;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.HeaderList;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MTextArea;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;

public class MainViewImpl implements MainView {
	private LayoutPanel main = new LayoutPanel();
	private HeaderPanel headerPanel = new HeaderPanel();
	private HeaderButton backButton = new HeaderButton();
	private Button syncButton = new Button();
	private AlertDialog alert = new AlertDialog("", "");
	private MTextArea text = new MTextArea();
	private HeaderList<Header, Content> headerList;

	public MainViewImpl() {
		main.setWidth("100%");
		backButton.setText("Back");
		syncButton.setText("Sync");
		headerPanel.setLeftWidget(backButton);
		headerPanel.setCenter("Welcome");
		text.setHeight("500px");
		main.add(headerPanel);
		GroupingCellList<Header, Content> groupingCellList = new GroupingCellList<Header, Content>(
				new ContentCell(), new HeaderCell());
		headerList = new HeaderList<Header, Content>(groupingCellList);

		main.add(headerList);
		main.add(syncButton);
		main.add(text);
	}

	public void alert(String text) {
		alert.setText(text);
		alert.show();
	}

	@Override
	public HasTapHandlers getBackButton() {
		return backButton;
	}
	
	@Override
	public HasTapHandlers getSyncButton() {
		return syncButton;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return main;
	}

	public String getText() {
		return text.getText();
	}

	@Override
	public HeaderPanel getHeaderPanel() {
		return headerPanel;
	}
	
	public void setText(String text) {
		this.text.setText(text);
	}

//	@Override
//	public HasText getHeader() {
//		return title;
//	}

	private static class ContentCell implements Cell<Content> {

		@Override
		public void render(SafeHtmlBuilder safeHtmlBuilder, Content model) {
			safeHtmlBuilder.appendEscaped(model.getName());

		}

		@Override
		public boolean canBeSelected(Content model) {
			return true;
		}

	}

	private static class HeaderCell implements Cell<Header> {

		@Override
		public void render(SafeHtmlBuilder safeHtmlBuilder, Header model) {
			safeHtmlBuilder.appendEscaped(model.getName());
		}

		@Override
		public boolean canBeSelected(Header model) {
			return false;
		}

	}

	@Override
	public void render(List<CellGroup<Header, Content>> models) {
		headerList.render(models);
	}
}
