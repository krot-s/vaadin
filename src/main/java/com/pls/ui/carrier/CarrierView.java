package com.pls.ui.carrier;

import java.io.Serializable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.domain.Carrier;
import com.pls.service.CarrierService;
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
	
	@SuppressWarnings("serial")
	private void initLayout(){
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(hearder);
		layout.setDebugId("CarrierView.initLayout.layout");

		final BeanContainer<Long, Carrier> beans = new BeanContainer<Long, Carrier>(Carrier.class);
		beans.setBeanIdProperty("id");
		beans.addAll(service.getAllCarriers());
		
		final BeanValidationForm<Carrier> form = new BeanValidationForm<Carrier>(Carrier.class);
		form.setImmediate(true);
		
		form.setCaption("Create new carrier");
		setDataSource(form);
		Button addButton = new Button("Add");
		addButton.addListener(new Button.ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {	
				try {
                    form.validate();
    				service.addCarrier((Carrier)form.getData());
    				setDataSource(form);
    				beans.addAll(service.getAllCarriers());		
                } catch (InvalidValueException e) {
                	MessageBox mb = new MessageBox(application.getMainWindow(), "Error", MessageBox.Icon.ERROR, e.getMessage().toString(),new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
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
		Carrier carrier = new Carrier();
		form.setData(carrier);
		form.setFormFieldFactory(new CustomFormFieldFactory());
		form.setItemDataSource(new BeanItem<Carrier>(carrier));		
	}
	
	private Table createTable(BeanContainer<Long, Carrier> beans){
		final Table table = new Table();
		table.setWidth("100%");
		table.setDebugId("CarrierView.initLayout.table");
		table.setColumnReorderingAllowed(true);
		table.setEditable(true);
		table.setSelectable(true);
		table.setContainerDataSource(beans);
		table.setTableFieldFactory(new CustomTableFieldFactory<Carrier>(Carrier.class));
		return table;
	}

}
