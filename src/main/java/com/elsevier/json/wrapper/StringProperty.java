package com.elsevier.json.wrapper;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class StringProperty extends BaseProperty implements Property {
	public StringProperty(String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
	}
	
	@Override
	public Object get() {
		return asText();
	}

	@Override
	public void set(Object value) {
		setAsText((String)value);
	}


}
