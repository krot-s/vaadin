package com.pls.ui.components;

import java.math.BigDecimal;
import java.util.HashMap;

import com.pls.domain.Carrier;
import com.pls.domain.CarrierStatus;
import com.pls.domain.Customer;
import com.pls.domain.CustomerStatus;
import com.pls.service.CarrierService;
import com.pls.service.CustomerService;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

public class CustomTableFieldFactory extends DefaultFieldFactory {
	private static final long serialVersionUID = 1L;

	// Map to find a field component by its item ID and property ID
	private final HashMap<Object, HashMap<Object, Field>> fields = new HashMap<Object, HashMap<Object, Field>>();

	private CustomerService customerService;
	private CarrierService carrierService;
	
	
	public CustomTableFieldFactory() {
		super();
	}
	
	public CustomTableFieldFactory(CarrierService carrierService, CustomerService customerService) {
		super();
		
		this.carrierService = carrierService;
		this.customerService = customerService;
	}
	
	@SuppressWarnings("serial")
	@Override
	public Field createField(final Container container, final Object itemId,
			Object propertyId, final Component uiContext) {
		if (propertyId.equals("id")) {
			return null;
		}

		Property containerProperty = container.getContainerProperty(itemId,
				propertyId);
		Class<?> type = containerProperty.getType();

		if (type.equals(String.class) || type.equals(Long.class) || type.equals(BigDecimal.class) || type.equals(Integer.class)) {
			final TextField tf = new TextField();
			tf.setImmediate(true);
			// Manage the field in the field storage
			HashMap<Object, Field> itemMap = fields.get(itemId);

			if (itemMap == null) {
				itemMap = new HashMap<Object, Field>();
				fields.put(itemId, itemMap);
			}
			itemMap.put(propertyId, tf);

			tf.setReadOnly(true);

			tf.addListener(new FocusListener() {
				public void focus(FocusEvent event) {
					// Make the entire item editable
					HashMap<Object, Field> itemMap = fields.get(itemId);
					for (Field f : itemMap.values())
						f.setReadOnly(false);

					Table table = (Table) uiContext;
					table.select(itemId);
				}
			});
			tf.addListener(new BlurListener() {
				public void blur(BlurEvent event) {
					// Make the entire item read-only
					HashMap<Object, Field> itemMap = fields.get(itemId);
					for (Field f : itemMap.values())
						f.setReadOnly(true);
				}
			});

			tf.setCaption(createCaptionByPropertyId(propertyId));
			return tf;
		} else if (type.equals(CarrierStatus.class)) {
			// dirty hack. remove after test!
			Select select = new Select();
			for (CarrierStatus cs : CarrierStatus.values()) {
				select.addItem(cs);
				select.setItemCaption(cs, cs.getHumanReadble());
			}
			select.setValue(propertyId);
			return select;
		} else if (type.equals(CustomerStatus.class)) {
			// dirty hack. remove after test!
			Select select = new Select();
			for (CustomerStatus cs : CustomerStatus.values()) {
				select.addItem(cs);
				select.setItemCaption(cs, cs.getHumanReadble());
			}
			select.setValue(propertyId);
			return select;
		} else if (type.equals(Carrier.class)) {			
			//return createCarrierField(container, itemId, propertyId, uiContext);
			Select select = new Select();
			for (Carrier carrier : carrierService.getAllCarriers()) {
				select.addItem(carrier);				
				select.setItemCaption(carrier, carrier.getName());
			}

			select.setImmediate(true);
			select.setNullSelectionAllowed(false);
			
			return select;
		} else if (type.equals(Customer.class)) {
			Select select = new Select();
			
			for (Customer customer : customerService.getAllCustomers()) {
				select.addItem(customer);
				select.setItemCaption(customer, customer.toString());
			}
			
			select.setImmediate(true);
			select.setNullSelectionAllowed(false);
			
			return select;
		}

		return super.createField(container, itemId, propertyId, uiContext);
	}
	
	@SuppressWarnings("serial")
	private Field createCarrierField(final Container container, final Object itemId,
			Object propertyId, final Component uiContext) {
		
		Property containerProperty = container.getContainerProperty(itemId,
				propertyId);
		
		
		
		Select select = new Select();
		for (Carrier carrier : carrierService.getAllCarriers()) {
			select.addItem(carrier);
			select.setItemCaption(carrier, carrier.toString());
		}
		select.setValue(propertyId);
		select.setImmediate(true);

		HashMap<Object, Field> itemMap = fields.get(itemId);

		if (itemMap == null) {
			itemMap = new HashMap<Object, Field>();
			fields.put(itemId, itemMap);
		}
		itemMap.put(propertyId, select);

		select.setReadOnly(true);
		
		select.addListener(new FocusListener() {
			
			@Override
			public void focus(FocusEvent event) {
				HashMap<Object, Field> itemMap = fields.get(itemId);
				for (Field f : itemMap.values())
					f.setReadOnly(false);

				Table table = (Table) uiContext;
				table.select(itemId);
			}
		});
		
		select.addListener(new BlurListener() {
			
			@Override
			public void blur(BlurEvent event) {
				HashMap<Object, Field> itemMap = fields.get(itemId);
				for (Field f : itemMap.values())
					f.setReadOnly(true);
			}
		});

		select.setValue(propertyId);
		return select;
	}
}
