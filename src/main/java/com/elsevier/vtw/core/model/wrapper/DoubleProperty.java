package com.elsevier.vtw.core.model.wrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DoubleProperty extends BaseProperty implements Property {
	public DoubleProperty(String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
	}
	
	@Override
	public Object get() {
		JsonNode node = asJsonNode();
		if (node!=null) {
			return node.asDouble();
		}
		return null;
	}

	@Override
	public void set(Object value) {
		jsonData.put(fieldName, (Double)value);
	}

}
