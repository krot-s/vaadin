package com.pls.ui.menu;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.pls.ui.carrier.CarriersViewShowEvent;
import com.pls.ui.customer.CustomerViewShowEvent;
import com.pls.ui.load.LoadViewShowEvent;
import com.pls.ui.user.UserViewShowEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public class MainMenu extends CustomComponent {
	private static final long serialVersionUID = 1L;

	private final EventBus eventBus;
	
	@Inject
	public MainMenu(EventBus eventBus) {
		this.eventBus = eventBus;
		initLayout();
	}
	
	private void initLayout(){
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(createMenu());
		layout.setSizeUndefined();
		setCompositionRoot(layout);
	}
	
	private MenuBar createMenu(){
		MenuBar menuBar = new MenuBar();		
		MenuBar.MenuItem plsMenu = menuBar.addItem("PLS menu", null);
		MenuBar.MenuItem carriers = plsMenu.addItem("Carriers", null);
		carriers.addItem("Search carriers", 
				new MenuCommand<CarriersViewShowEvent>(new CarriersViewShowEvent()));
		carriers.addItem("Create carrier", 
				new MenuCommand<CarriersViewShowEvent>(new CarriersViewShowEvent()));
				
		MenuBar.MenuItem customers = plsMenu.addItem("Customers", null);
		customers.addItem("Search customers", 
				new MenuCommand<CustomerViewShowEvent>(new CustomerViewShowEvent()));
		customers.addItem("Create customer", 
				new MenuCommand<CustomerViewShowEvent>(new CustomerViewShowEvent()));
		
		MenuBar.MenuItem loads = plsMenu.addItem("Loads", null);
		loads.addItem("Search loads", 
				new MenuCommand<LoadViewShowEvent>(new LoadViewShowEvent()));
		loads.addItem("Create load", 
				new MenuCommand<LoadViewShowEvent>(new LoadViewShowEvent()));
		
		MenuBar.MenuItem users = plsMenu.addItem("Users", null);
		users.addItem("Add/Search Users", 
				new MenuCommand<UserViewShowEvent>(new UserViewShowEvent()));

		
		menuBar.setSizeUndefined();
		return menuBar;
	}
	
	private class MenuCommand<T> implements Command {
		private static final long serialVersionUID = 1L;

		private T event; 
		private MenuCommand(T event) {
			this.event = event;
		}
		
		@Override
		public void menuSelected(MenuItem selectedItem) {
			eventBus.post(event);
		}
	}
}
