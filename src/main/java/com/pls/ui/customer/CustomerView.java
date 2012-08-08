package com.pls.ui.customer;

import java.io.Serializable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.domain.Customer;
import com.pls.service.CustomerService;
import com.pls.ui.components.CustomFormFieldFactory;
import com.pls.ui.components.CustomTableFieldFactory;
import com.pls.ui.menu.HeaderStrip;
import com.vaadin.Application;
import com.vaadin.addon.beanvalidation.BeanValidationForm;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

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
		
		final BeanValidationForm<Customer> form = new BeanValidationForm<Customer>(Customer.class);
		form.setImmediate(false);
		form.setCaption("Create new customer");
		setDataSource(form);	
		Button addButton = new Button("Create");
		addButton.addListener(new Button.ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {						
				try {
                    form.validate();
    				service.addCustomer((Customer)form.getData());
    				setDataSource(form);
    				beans.addAll(service.getAllCustomers());		
                } catch (InvalidValueException e) {
                	MessageBox mb = new MessageBox(application.getMainWindow(), "Error", MessageBox.Icon.ERROR, 
                				"Please correct form errors and try again", new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
					mb.show();
                }				
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
		form.setFormFieldFactory(new CustomFormFieldFactory());
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
		table.setTableFieldFactory(new CustomTableFieldFactory<Customer>(Customer.class));
		table.setColumnHeader("contactName", "Contact Name");
		table.setColumnHeader("id", "ID");
		table.setColumnHeader("name", "Name");
		table.setColumnHeader("status", "Status");
		table.setColumnHeader("taxId", "Federal Tax ID");
		table.setColumnHeader("validUntil", "Valid Until");
		return table;
	}

}
