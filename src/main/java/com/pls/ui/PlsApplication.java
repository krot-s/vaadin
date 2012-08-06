package com.pls.ui;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.pls.ui.carrier.CarrierView;
import com.pls.ui.carrier.CarriersViewShowEvent;
import com.vaadin.ui.Window;


public class PlsApplication extends com.vaadin.Application{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventBus eventBus;
	
	/**
	 * Next views are injected here in order to initialize them at the same time Application
	 * is initialized. This is becase they register themselves in EventBus.
	 * This is dirty hack. MVP usage in real project with remove this hack as it will be 
	 * nnecessary. 
	 * Do not remove these injections, otherwise application will stop working  
	 */
	@Inject
	private CarrierView carrierView;

	
	@Override
	public void init() {
		initMainWindow();		
		eventBus.post(new CarriersViewShowEvent());
		System.out.println("New application created");
	}
	
	private void initMainWindow(){
		setMainWindow(new Window("Vaadin Application"));
	}
}
