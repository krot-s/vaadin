package com.pls.ui.menu;

import com.google.inject.Inject;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class HeaderStrip extends CustomComponent {
	private static final long serialVersionUID = 1L;

	@Inject
	public HeaderStrip(MainMenu mainMenu) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(mainMenu);

		layout.addComponent(new Button("Financial Dashboard", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				getWindow().showNotification("Financial Dashboard click");
			}
		}));
		
		layout.setSizeUndefined();
		setCompositionRoot(layout);
	}
}