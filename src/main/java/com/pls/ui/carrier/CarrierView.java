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
		layout.setDebugId("CarrierView.initLayout.layout");

		BeanContainer<Long, Carrier> beans = new BeanContainer<Long, Carrier>(Carrier.class);
		beans.setBeanIdProperty("id");
		beans.addAll(service.getAllCarriers());
		
		CustomTable table = new CustomTable("Carrier", beans);
		table.setDebugId("CarrierView.initLayout.table");
		layout.addComponent(table);
		
		application.getMainWindow().setContent(layout);
	}
}
