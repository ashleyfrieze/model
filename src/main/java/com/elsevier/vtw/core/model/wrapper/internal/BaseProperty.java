package com.elsevier.vtw.core.model.wrapper.internal;

import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class BaseProperty {
	protected String fieldName;
	protected ObjectNode jsonData;
	
	public BaseProperty(String fieldName, ObjectNode jsonData) {
		this.fieldName = fieldName;
		this.jsonData = jsonData;
	}
	
	protected String asText() {
		return jsonData.get(fieldName).asText();
	}

	protected void setAsText(String value) {
		jsonData.put(fieldName, value);
	}
}
