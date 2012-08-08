package com.pls.ui.menu;

import com.google.inject.Inject;
import com.pls.ui.PlsApplication;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class HeaderStrip extends CustomComponent {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlsApplication application;	

	@Inject
	public HeaderStrip(MainMenu mainMenu) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addComponent(mainMenu);

		layout.addComponent(new Button("Financial Dashboard", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getWindow().showNotification("Financial Dashboard click");
			}
		}));
		
		layout.addComponent(new Button("Logout", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				application.closeApplication();
			}
		}));
		
		layout.setSizeUndefined();
		setCompositionRoot(layout);
	}
}
