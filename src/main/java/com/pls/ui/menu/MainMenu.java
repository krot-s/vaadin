package com.pls.ui.menu;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

@SuppressWarnings("serial")
public class MainMenu extends CustomComponent {
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
		carriers.addItem("Search carriers", null);
		carriers.addItem("Create carrier", null);
				
		MenuBar.MenuItem customers = plsMenu.addItem("Customers", null);
		customers.addItem("Search customers", null);
		customers.addItem("Create customer", null);
		
		MenuBar.MenuItem loads = plsMenu.addItem("Loads", null);
		loads.addItem("Search loads", null);
		loads.addItem("Create load", null);
		
		menuBar.setSizeUndefined();
		return menuBar;
	}
	
	private class MenuCommand<T> implements Command {
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
