package com.elsevier.json.wrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WrapperProperty extends BaseProperty implements Property {
	@SuppressWarnings("rawtypes")
	private Class type;
	
	public WrapperProperty(@SuppressWarnings("rawtypes") Class type, String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object get() {
		JsonNode node = jsonData.get(fieldName);
		if (node!=null && node instanceof ObjectNode) {
			return WrapperFactory.create(type, (ObjectNode)node);
		}
		return null;
	}

	@Override
	public void set(Object value) {
		// assign the guts of the json into the object
		Wrapper jsonWrapper = (Wrapper)value;
		jsonData.set(fieldName, jsonWrapper.json());		
	}

}
