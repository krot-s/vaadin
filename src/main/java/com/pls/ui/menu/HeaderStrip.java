package com.pls.ui.menu;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.pls.ui.PlsApplication;
import com.pls.ui.carrier.CarriersViewShowEvent;
import com.pls.ui.customer.CustomerViewShowEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class HeaderStrip extends CustomComponent {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PlsApplication application;	
	
	@Inject
	private EventBus eventBus;

	@Inject
	public HeaderStrip(MainMenu mainMenu) {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.addComponent(mainMenu);

		layout.addComponent(new Button("Financial Dashboard", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				getWindow().showNotification("Financial Dashboard click");
			}
		}));
		
		layout.addComponent(new Button("Carriers", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				eventBus.post(new CarriersViewShowEvent());
			}
		}));

		layout.addComponent(new Button("Customers", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				eventBus.post(new CustomerViewShowEvent());
			}
		}));

		layout.addComponent(new Button("Logout", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				application.closeApplication();
			}
		}));
		
		layout.setSizeUndefined();
		setCompositionRoot(layout);
	}
}
