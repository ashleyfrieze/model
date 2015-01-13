package com.elsevier.vtw.core.model.wrapper.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Acts as a dynamic proxy to the json data
 */
public class WrapperInvocationHandler<T> implements InvocationHandler {
	private ObjectNode jsonData;
	private Class<T> wrappedType;
	
	private static Map<Method, InvocationDefinition> invocationDefinitions = new HashMap<>();
	private static Object invocationDefinitionsLock = new Object();
	
	private static final String GETTER_PREFIX = "get";
	private static final String SETTER_PREFIX = "set";
	
	/**
	 * How to handle the invocation
	 */
	interface HandlingStrategy {
		Object handle(Object[] parameters);
	}
	
	/**
	 * Instructions on what a given method means in terms of choosing invocation strategy
	 */
	static class InvocationDefinition {
		String fieldName;
		boolean isGetter;
		boolean isSetter;
	}
	
	/**
	 * Constructor
	 * @param wrappedType the type that is being proxied
	 * @param jsonData the place where the json data is being stored/wrapped
	 */
	public WrapperInvocationHandler(Class<T> wrappedType, ObjectNode jsonData) {
		this.wrappedType = wrappedType;
		this.jsonData = jsonData;
	}
	
	@Override
	public Object invoke(Object arg0, Method method, Object[] arg2)
			throws Throwable {
		HandlingStrategy strategy = findInvocationStrategy(method);
		if (strategy!=null) {
			return strategy.handle(arg2);
		} if (method.equals(Wrapper.class.getMethod("json"))) {
			return jsonData;
		} else {
			throw new UnsupportedOperationException("This method cannot be called");
		}
	}

	private HandlingStrategy findInvocationStrategy(Method method) throws Throwable {
		InvocationDefinition definition = getInvocationDefinition(method);
		if (definition!=null) {
			if (definition.isGetter) {
				return createStringGetter(definition.fieldName);
			} else if (definition.isSetter) {
				return createStringSetter(definition.fieldName);
			}
		}
		
		return null;
	}

	private InvocationDefinition getInvocationDefinition(Method method) {
		synchronized(invocationDefinitionsLock) {
			// look it up if possible
			InvocationDefinition def = invocationDefinitions.get(method);
			if (def!=null) {
				return def;
			}
			
			// build and cache the invocation definition
			def = buildInvocationDefinition(method);
			invocationDefinitions.put(method, def);
			return def;
		}
	}

	private InvocationDefinition buildInvocationDefinition(Method method) {
		Field annotation = getFieldAnnotation(method);
		if(annotation!=null) {
			InvocationDefinition def = new InvocationDefinition();
			def.fieldName = annotation.value();
			def.isGetter = method.getName().startsWith(GETTER_PREFIX);
			def.isSetter = method.getName().startsWith(SETTER_PREFIX);
			return def;
		}
		return null;
	}

	/**
	 * Find the {link:Field} annotation on this method or its sibling if a getter/setter
	 * @param method the method to review
	 * @return {link:Field} annotation from this method, if available, or its sibling if not
	 */
	private Field getFieldAnnotation(Method method) {
		Field field = getFieldAnnotationFrom(method);
		if (field!=null) {
			return field;
		}
		
		// perhaps this is a getter/setter where the other one has the
		// annotation
		if (method.getName().startsWith(GETTER_PREFIX)) {
			return getFieldAnnotationForRePrefixed(method, SETTER_PREFIX);
		}
		if (method.getName().startsWith(SETTER_PREFIX)) {
			return getFieldAnnotationForRePrefixed(method, GETTER_PREFIX);
		}
		
		return null;
	}

	private Field getFieldAnnotationFrom(Method method) {
		return method.getAnnotation(Field.class);
	}

	private Field getFieldAnnotationForRePrefixed(Method method, String otherPrefix) {
		Method[] methods = wrappedType.getMethods();
		String newName = otherPrefix + method.getName().substring(SETTER_PREFIX.length());
		for(Method possibleMatch:methods) {
			if (possibleMatch.getName().equals(newName)) {
				return getFieldAnnotationFrom(possibleMatch);
			}
		}
		return null;
	}

	private HandlingStrategy createStringGetter(final String fieldName) {
		return new HandlingStrategy() {
			@Override
			public Object handle(Object[] parameters) {
				return new StringProperty(fieldName, jsonData).get();
			}
		};
	}
	
	private HandlingStrategy createStringSetter(final String fieldName) {
		return new HandlingStrategy() {
			@Override
			public Object handle(Object[] parameters) {
				new StringProperty(fieldName, jsonData).set((String)parameters[0]);
				return null;
			}
		};
	}

}
