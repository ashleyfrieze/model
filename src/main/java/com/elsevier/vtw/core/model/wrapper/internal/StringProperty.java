package com.elsevier.vtw.core.model.wrapper.internal;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class StringProperty extends BaseProperty<String> implements Property<String> {
	public StringProperty(String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
	}
	
	@Override
	public String get() {
		return asText();
	}

	@Override
	public void set(String value) {
		setAsText(value);
	}


}
