package com.elsevier.vtw.core.model.wrapper.internal;

import java.lang.reflect.Proxy;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class WrapperFactory {

	public static<T extends Wrapper> T create(Class<T> type) {
		JsonNodeFactory factory = JsonNodeFactory.instance;
		return create(type, new ObjectNode(factory));
	}	
	
	@SuppressWarnings("unchecked")
	public static<T extends Wrapper> T create(Class<T> type, ObjectNode root) {
		WrapperInvocationHandler handler = new WrapperInvocationHandler(root);
		return (T)Proxy.newProxyInstance(type.getClassLoader(), new Class[] {type}, handler);
	}

}
