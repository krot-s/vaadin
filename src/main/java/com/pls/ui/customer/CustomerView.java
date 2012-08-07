package com.pls.ui.customer;

import java.io.Serializable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.domain.Customer;
import com.pls.service.CustomerService;
import com.pls.ui.components.CustomTableFieldFactory;
import com.pls.ui.menu.HeaderStrip;
import com.vaadin.Application;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class CustomerView implements Serializable {
	private static final long serialVersionUID = -7921194304196825064L;

	@Inject
	private Application application;
	
	@Inject
	private CustomerService service;
	
	@Inject
	private HeaderStrip hearder;

	@Inject
	public CustomerView(EventBus eventBus) {
		eventBus.register(this);
	}
	
	@Subscribe
	public void showView(CustomerViewShowEvent event){
		initLayout();
	}
	
	@SuppressWarnings("serial")
	private void initLayout(){
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(hearder);
		layout.setDebugId("CustomerView.initLayout.layout");

		final BeanContainer<Long, Customer> beans = new BeanContainer<Long, Customer>(Customer.class);
		beans.setBeanIdProperty("id");
		beans.addAll(service.getAllCustomers());
		
		final Form form = new Form();
		form.setCaption("Create new customer");
		setDataSource(form);
				
		Button addButton = new Button("Add");
		addButton.addListener(new Button.ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {						
				service.addCustomer((Customer)form.getData());
				setDataSource(form);
				beans.addAll(service.getAllCustomers());				
			}
		});

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(addButton);
		form.getFooter().addComponent(buttons);

		layout.addComponent(createTable(beans));
		layout.addComponent(form);

		application.getMainWindow().setContent(layout);
	}
	
	private void setDataSource(Form form){
		Customer customer = new Customer();
		form.setData(customer);
		form.setItemDataSource(new BeanItem<Customer>(customer));		
	}
	
	private Table createTable(BeanContainer<Long, Customer> beans){
		final Table table = new Table();		
		table.setWidth("100%");
		table.setDebugId("CarrierView.initLayout.table");
		table.setColumnReorderingAllowed(true);
		table.setEditable(true);
		table.setSelectable(true);
		table.setContainerDataSource(beans);
		table.setTableFieldFactory(new CustomTableFieldFactory());
		return table;
	}

}
