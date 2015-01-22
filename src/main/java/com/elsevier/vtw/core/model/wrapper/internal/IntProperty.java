package com.elsevier.vtw.core.model.wrapper.internal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class IntProperty extends BaseProperty implements Property {
	public IntProperty(String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
	}
	
	@Override
	public Object get() {
		JsonNode node = asJsonNode();
		if (node!=null) {
			return node.asInt();
		}
		return null;
	}

	@Override
	public void set(Object value) {
		jsonData.put(fieldName, (Integer)value);
	}

}
