package com.pls.ui.carrier;

import java.io.Serializable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.domain.Carrier;
import com.pls.service.CarrierService;
import com.pls.ui.components.CustomTable;
import com.pls.ui.menu.HeaderStrip;
import com.vaadin.Application;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
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
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(hearder);
		layout.setDebugId("CarrierView.initLayout.layout");

		final BeanContainer<Long, Carrier> beans = new BeanContainer<Long, Carrier>(Carrier.class);
		beans.setBeanIdProperty("id");
		beans.addAll(service.getAllCarriers());
		
		final CustomTable table = new CustomTable("Carries table", beans);
		table.setDebugId("CarrierView.initLayout.table");

		final Carrier carrier = new Carrier();
		final Form form = new Form();
		form.setCaption("Create new carrier");
		
		final BeanItem<Carrier> formDatasource = new BeanItem<Carrier>(carrier);
		form.setItemDataSource(formDatasource);
		
		Button addButton = new Button("Add");
		addButton.addListener(new Button.ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {
				service.addCarrier(carrier);
				beans.addAll(service.getAllCarriers());				
			}
		});

		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(addButton);
		form.getFooter().addComponent(buttons);

		layout.addComponent(table);
		layout.addComponent(form);

		application.getMainWindow().setContent(layout);
	}
}
