package com.google.gwt.sample.mvpademo.client.activities.main;

import java.util.List;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.CellGroup;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;

public interface MainView extends IsWidget {
	void alert(String text);

	HasTapHandlers getBackButton();
	
	HasTapHandlers getSyncButton();

	public String getText();

	public HeaderPanel getHeaderPanel();
	
	public void setText(String text);
	

	//public HasText getHeader();

	public void render(List<CellGroup<Header, Content>> models);

	public class Header {
		private final String name;

		public Header(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	public class Content {

		private final String name;

		public Content(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
}
