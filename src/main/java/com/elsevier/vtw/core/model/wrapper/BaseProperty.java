package com.elsevier.vtw.core.model.wrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class BaseProperty implements Property {
	protected String fieldName;
	protected ObjectNode jsonData;
	
	public BaseProperty(String fieldName, ObjectNode jsonData) {
		this.fieldName = fieldName;
		this.jsonData = jsonData;
	}
	
	protected String asText() {
		JsonNode field = asJsonNode();
		return field!=null ? field.asText() : null;
	}

	protected JsonNode asJsonNode() {
		JsonNode field = jsonData.get(fieldName);
		return field;
	}

	protected void setAsText(String value) {
		jsonData.put(fieldName, value);
	}
	
	@Override
	public void updateJsonRoot(ObjectNode jsonData) {
		this.jsonData = jsonData;
	}
}
