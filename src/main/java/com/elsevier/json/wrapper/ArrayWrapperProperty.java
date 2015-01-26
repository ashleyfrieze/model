package com.elsevier.json.wrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ArrayWrapperProperty extends BaseProperty implements Property {
	private Class<?> wrapperType;
	
	public ArrayWrapperProperty(Class<?> wrapperType, String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
		this.wrapperType = wrapperType;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object get() {
		JsonNode node = jsonData.get(fieldName);
		if (node == null) {
			return null;
		}
		return new ArrayWrapper(wrapperType, node);
	}

	@Override
	public void set(Object value) {
		((ArrayWrapper)value).insertInto(jsonData, fieldName);
	}

}
