package com.pls.ui.components;

import java.util.HashMap;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class CustomTable extends CustomComponent {

	private static final long serialVersionUID = 1L;
	
	public CustomTable(String tableName , BeanContainer<?, ?> beans) {

		VerticalLayout layout = new VerticalLayout();
		final Table table = new Table(tableName, beans);

		table.setColumnReorderingAllowed(true);
		table.setEditable(true);
		table.setSelectable(true);
		table.setContainerDataSource(beans);
		layout.addComponent(table);
		
		table.setSizeUndefined();
		layout.setSizeUndefined();
		setCompositionRoot(layout);
	

		// Map to find a field component by its item ID and property ID
		final HashMap<Object, HashMap<Object, Field>> fields = new HashMap<Object, HashMap<Object, Field>>();

		// table.setTableFieldFactory(new TableFieldFactory() {
		table.setTableFieldFactory(new DefaultFieldFactory() {
			private static final long serialVersionUID = -5741977060384915110L;

			public Field createField(Container container, final Object itemId,
					final Object propertyId, Component uiContext) {
				
				String typeId = container.getType(propertyId).toString();

				if (typeId.equals(String.class.toString())
						| typeId.equals(Long.class.toString())) {
					final TextField tf = new TextField();
					// Needed for the generated column
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
						private static final long serialVersionUID = 1006388127259206641L;

						public void focus(FocusEvent event) {
							// Make the entire item editable
							HashMap<Object, Field> itemMap = fields.get(itemId);
							for (Field f : itemMap.values())
								f.setReadOnly(false);

							table.select(itemId);
						}
					});
					tf.addListener(new BlurListener() {
						private static final long serialVersionUID = -4497552765206819985L;

						public void blur(BlurEvent event) {
							// Make the entire item read-only
							HashMap<Object, Field> itemMap = fields.get(itemId);
							for (Field f : itemMap.values())
								f.setReadOnly(true);
						}
					});

					return tf;
				}

				return super.createField(container, itemId, propertyId,
						uiContext);
			}
		});
	}
}