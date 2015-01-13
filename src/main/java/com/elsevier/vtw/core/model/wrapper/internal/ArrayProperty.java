package com.elsevier.vtw.core.model.wrapper.internal;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ArrayProperty<T> extends BaseProperty<T> {
	private Class<T> childType;
	
	public ArrayProperty(String fieldName, ObjectNode jsonData, Class<T> childType) {
		super(fieldName, jsonData);
	}

	T get(int index) {
		return null;
	}
}
