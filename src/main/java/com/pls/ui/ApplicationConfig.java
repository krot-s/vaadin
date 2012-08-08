package com.pls.ui;

import com.google.common.eventbus.EventBus;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;
import com.pls.service.CarrierService;
import com.pls.service.CarrierServiceImpl;
import com.pls.service.CustomerService;
import com.pls.service.CustomerServiceImpl;
import com.pls.service.LoadService;
import com.pls.service.LoadServiceImpl;
import com.pls.ui.carrier.CarrierView;
import com.pls.ui.customer.CustomerView;
import com.pls.ui.guice.GuiceApplicationServlet;
import com.pls.ui.load.LoadView;
import com.pls.ui.menu.HeaderStrip;
import com.pls.ui.menu.MainMenu;
import com.vaadin.Application;

public class ApplicationConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		ServletModule module = new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("/*").with(GuiceApplicationServlet.class);

				bind(EventBus.class).in(ServletScopes.SESSION);
				
				bind(CustomerService.class).to(CustomerServiceImpl.class).in(ServletScopes.SESSION);
				bind(CarrierService.class).to(CarrierServiceImpl.class).in(ServletScopes.SESSION);
				bind(LoadService.class).to(LoadServiceImpl.class).in(ServletScopes.SESSION);

				// bind application object as all views
				bind(Application.class).to(PlsApplication.class).in(ServletScopes.SESSION);
				bind(CarrierView.class).in(ServletScopes.SESSION);
				bind(CustomerView.class).in(ServletScopes.SESSION);
				bind(LoadView.class).in(ServletScopes.SESSION);
				bind(MainMenu.class).in(ServletScopes.SESSION);
				bind(HeaderStrip.class).in(ServletScopes.SESSION);
			}
		};

		Injector injector = Guice.createInjector(module);
		return injector;
	}
}