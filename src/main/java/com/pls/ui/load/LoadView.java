package com.pls.ui.load;

import java.io.Serializable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.pls.domain.Load;
import com.pls.service.CarrierService;
import com.pls.service.CustomerService;
import com.pls.service.LoadService;
import com.pls.ui.components.CustomTableFieldFactory;
import com.pls.ui.menu.HeaderStrip;
import com.vaadin.Application;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class LoadView implements Serializable {

	private static final long serialVersionUID = -1015803303420813251L;

	@Inject
	private Application application;
	
	@Inject
	private LoadService service;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private CarrierService carrierService;
	
	@Inject
	private HeaderStrip header;
	
	@Inject
	public LoadView(EventBus eventBus) {
		eventBus.register(this);
	}
	
	@Subscribe
	public void showView(LoadViewShowEvent event) {
		initLayout();
	}
	
	private void initLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		layout.addComponent(header);
		layout.setDebugId("LoadView.initLayout.layout");
		
		final BeanContainer<Long, Load> beans = new BeanContainer<Long, Load>(Load.class);
		beans.setBeanIdProperty("id");
		beans.addAll(service.getAllLoads());
		
		final Form form = new Form();
		form.setCaption("Create new load");
		setDataSource(form);
		
		Button addButton = new Button("Add");
		addButton.addListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				service.addLoad((Load)form.getData());
				setDataSource(form);
				beans.addAll(service.getAllLoads());
			}
		});
		
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.addComponent(addButton);
		form.getFooter().addComponent(buttons);

		layout.addComponent(createTable(beans));
		layout.addComponent(form);

		application.getMainWindow().setContent(layout);
	}
	
	private void setDataSource(Form form) {
		Load load = new Load();
		form.setData(load);
		form.setItemDataSource(new BeanItem<Load>(load));
	}
	
	private Table createTable(BeanContainer<Long, Load> beans){
		final Table table = new Table();		
		table.setWidth("100%");
		table.setDebugId("LoadView.initLayout.table");
		table.setColumnReorderingAllowed(true);
		table.setEditable(true);
		table.setSelectable(true);
		table.setContainerDataSource(beans);
		table.setTableFieldFactory(new CustomTableFieldFactory(this.carrierService, this.customerService));
		table.setColumnHeader("carrier", "Carrier");
		table.setColumnHeader("carrierRate", "Carrier Rate");
		table.setColumnHeader("customer", "Customer");
		table.setColumnHeader("customerRate", "Customer Rate");
		table.setColumnHeader("id", "ID");
		table.setColumnHeader("piecies", "Piecies");
		table.setColumnHeader("shipmentNumber", "Shipment Number");
		table.setColumnHeader("weight", "Weight");
		
		return table;
	}
}
