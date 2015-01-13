package com.elsevier.vtw.core.model.wrapper.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.elsevier.vtw.core.model.wrapper.ContentObjectMetadata;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Acts as a dynamic proxy to the json data
 */
public class WrapperInvocationHandler implements InvocationHandler {
	private ObjectNode jsonData;
	
	interface HandlingStrategy {
		Object handle(Object[] parameters);
	}
	
	public WrapperInvocationHandler(ObjectNode jsonData) {
		this.jsonData = jsonData;
	}
	
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2)
			throws Throwable {
		HandlingStrategy strategy = findInvocationStrategy(arg1);
		if (strategy!=null) {
			return strategy.handle(arg2);
		} if (arg1.equals(Wrapper.class.getMethod("json"))) {
			return jsonData;
		} else {
			throw new UnsupportedOperationException("This method cannot be called");
		}
	}

	private HandlingStrategy findInvocationStrategy(Method arg1) throws Throwable {
		if (arg1.isAnnotationPresent(Field.class)) {
			final String fieldName = arg1.getAnnotation(Field.class).value();

			return new HandlingStrategy() {
				@Override
				public Object handle(Object[] parameters) {
					return new StringProperty(fieldName, jsonData);
				}
			};
		}
		
		return null;
	}

}
