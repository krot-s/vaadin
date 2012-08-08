package com.pls.ui.components;

import com.pls.domain.CarrierStatus;
import com.pls.domain.CustomerStatus;
import com.vaadin.data.Item;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Select;

public class CustomFormFieldFactory extends DefaultFieldFactory {

	private static final long serialVersionUID = -946527409294078339L;

	@Override
	public Field createField(Item item, Object propertyId, Component uiContext) {
		Class<?> type = item.getItemProperty(propertyId).getType();
		if (type.equals(CarrierStatus.class)) {
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
		}
		return super.createField(item, propertyId, uiContext);
	}
}
