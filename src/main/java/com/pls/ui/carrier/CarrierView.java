package com.pls.ui.carrier;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.ui.menu.MainMenu;
import com.vaadin.Application;
import com.vaadin.ui.VerticalLayout;

public class CarrierView {
	@Inject
	private Application application;
	
	@Inject
	private MainMenu mainMenu;

	@Inject
	public CarrierView(EventBus eventBus) {
		eventBus.register(this);
	}
	
	@Subscribe
	public void showView(CarriersViewShowEvent event){
		initLayout();
	}
	
	private void initLayout(){
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(mainMenu);
		
		application.getMainWindow().setContent(layout);
	}
}
