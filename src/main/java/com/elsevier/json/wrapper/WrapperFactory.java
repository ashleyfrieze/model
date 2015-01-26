package com.elsevier.json.wrapper;

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
		return (T)createUnchecked(type,root);
	}

	// for internal use only where runtime type information is all that can be used
	// where the <T extends wrapper> constraint can't be honoured
	protected static Object createUnchecked(@SuppressWarnings("rawtypes") Class type, ObjectNode root) {
		WrapperInvocationHandler<Object> handler = new WrapperInvocationHandler<Object>(type, root);
		return Proxy.newProxyInstance(type.getClassLoader(), new Class[] {type}, handler);
	}
}
