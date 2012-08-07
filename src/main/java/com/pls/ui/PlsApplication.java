package com.pls.ui;

import javax.servlet.http.HttpSession;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.pls.ui.carrier.CarrierView;
import com.pls.ui.carrier.CarriersViewShowEvent;
import com.pls.ui.customer.CustomerView;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;


public class PlsApplication extends com.vaadin.Application{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventBus eventBus;
	
	@Inject
	private HttpSession session;
	
	/**
	 * Next views are injected here in order to initialize them at the same time Application
	 * is initialized. This is becase they register themselves in EventBus.
	 * This is dirty hack. MVP usage in real project with remove this hack as it will be 
	 * nnecessary. 
	 * Do not remove these injections, otherwise application will stop working  
	 */
	@Inject
	private CarrierView carrierView;

	@Inject
	private CustomerView customerView;
	
	@Override
	public void init() {
		initMainWindow();		
		eventBus.post(new CarriersViewShowEvent());
		System.out.println("New application created");
	}
	
	private void initMainWindow(){
		setTheme("runo");
		setMainWindow(new Window("Vaadin Application"));		
		getMainWindow().addListener(new Window.CloseListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void windowClose(CloseEvent e) {
				closeApplication();
			}
		});
		setLogoutURL("/");
	}
	
	public void closeApplication(){
		System.out.println("closingggg!!!!!!!!!!!!!!!!!");
		session.invalidate();
		close();
	}
}
