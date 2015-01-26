package com.elsevier.vtw.core.model.wrapper;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class NormalisingWrapperProperty implements Property {
	@SuppressWarnings("rawtypes")
	private Class type;
	private ObjectNode jsonData;
	
	public NormalisingWrapperProperty(@SuppressWarnings("rawtypes") Class type, ObjectNode jsonData) {
		this.type = type;
		this.jsonData = jsonData;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object get() {
		return WrapperFactory.create(type, jsonData);
	}

	@Override
	public void set(Object value) {
		throw new UnsupportedOperationException("Cannot set a normalised sub object");
		
	}

	@Override
	public void updateJsonRoot(ObjectNode root) {
		throw new UnsupportedOperationException("Cannot use normalisation with re-rooting");
		
	}

}
