package com.pls.ui.carrier;

import java.io.Serializable;
import java.util.Calendar;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.domain.Carrier;
import com.pls.domain.CarrierStatus;
import com.pls.service.CarrierService;
import com.pls.ui.components.CustomTable;
import com.pls.ui.menu.HeaderStrip;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.FieldFactory;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.Window;

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
		Panel  panel = new Panel();
		Panel  inpanel = new Panel();
		Panel  addpanel = new Panel("Create new Carrier");
		inpanel.setWidth("1200px");
		addpanel.setWidth("1151px");
		
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(hearder);

		final BeanContainer<Long, Carrier> beans = new BeanContainer<Long, Carrier>(Carrier.class);
		beans.setBeanIdProperty("id");
		beans.addAll(service.getAllCarriers());
		
		final CustomTable table = new CustomTable("Carrier", beans);
		table.setHeight("500px");
		final Carrier carrier = new Carrier();
		
		carrier.setId((long) 0);
		carrier.setContactName("");
		carrier.setName("");
		carrier.setStatus(CarrierStatus.ACTIVE);
		carrier.setTaxId("");
		carrier.setValidUntil(Calendar.getInstance().getTime());
		carrier.setMcNumber((long) 0);
		carrier.setScac("");
	
		final Form form = new Form();

		BeanItem<Carrier> bItem = new BeanItem<Carrier>(carrier);
		form.setItemDataSource(bItem);
		
		Button addBean = new Button("Add");
		addBean.addListener(new ClickListener() {	
			@Override
			public void buttonClick(ClickEvent event) {
				service.addCarrier(carrier);
				beans.addAll(service.getAllCarriers());

			}
		});
		
		inpanel.addComponent(table);
		addpanel.addComponent(form);
		addpanel.addComponent(addBean);
		inpanel.addComponent(addpanel);
		panel.addComponent(inpanel);
		layout.addComponent(panel);
		//layout.addComponent(addBean);
		
		application.getMainWindow().setContent(layout);
		//application.getMainWindow().setContent(panel);
	}
}
