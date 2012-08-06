package com.pls.ui.carrier;

import java.util.Date;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.domain.Carrier;
import com.pls.domain.CarrierStatus;
import com.pls.ui.components.CustomTable;
import com.pls.ui.menu.MainMenu;
import com.vaadin.Application;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.VerticalLayout;

public class CarrierView {
	@Inject
	private Application application;
	
	@Inject
	private MainMenu mainMenu;

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
		layout.addComponent(mainMenu);

		BeanContainer<Long, Carrier> bean = new BeanContainer<Long, Carrier>(
				Carrier.class);
		bean.setBeanIdProperty("id");

		for (int i = 0; i < 10; i++) {
			Carrier carrier = new Carrier();
			carrier.setId((long) i);
			carrier.setContactName(String.valueOf(i));
			carrier.setMcNumber((long) i);
			carrier.setName(String.valueOf(i));
			carrier.setScac(String.valueOf(i));
			carrier.setStatus(CarrierStatus.ACTIVE);
			carrier.setTaxId(String.valueOf(i));
			carrier.setValidUntil(new Date());
			bean.addBean(carrier);
		}
		
		CustomTable table = new CustomTable("Carrier",bean);
		layout.addComponent(table);
		
		application.getMainWindow().setContent(layout);
	}
}
