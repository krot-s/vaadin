package com.pls.ui.carrier;

import java.io.Serializable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.service.CarrierService;
import com.pls.ui.menu.HeaderStrip;
import com.vaadin.Application;
import com.vaadin.ui.VerticalLayout;

public class CarrierView implements Serializable {
	private static final long serialVersionUID = -7921194304196825064L;

	@Inject
	private Application application;
	
	@Inject
	private CarrierService service;
	
	@Inject
	private HeaderStrip hearder;

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
		layout.addComponent(hearder);
		
		application.getMainWindow().setContent(layout);
	}
}
