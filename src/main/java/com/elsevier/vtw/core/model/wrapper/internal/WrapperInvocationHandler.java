package com.elsevier.vtw.core.model.wrapper.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Acts as a dynamic proxy to the json data
 */
public class WrapperInvocationHandler<T> implements InvocationHandler {
	private ObjectNode jsonData;
	private Class<T> wrappedType;
	
	private static final String GETTER_PREFIX = "get";
	private static final String SETTER_PREFIX = "set";
	
	
	interface HandlingStrategy {
		Object handle(Object[] parameters);
	}
	
	class InvocationDefinition {
		String fieldName;
		boolean isGetter;
		boolean isSetter;
	}
	
	public WrapperInvocationHandler(Class<T> wrappedType, ObjectNode jsonData) {
		this.wrappedType = wrappedType;
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
		
		InvocationDefinition definition = getInvocationDefinition(arg1);
		if (definition!=null) {
			if (definition.isGetter) {
				return createStringGetter(definition.fieldName);
			} else if (definition.isSetter) {
				return createStringSetter(definition.fieldName);
			}
		}
		
		return null;
	}

	private InvocationDefinition getInvocationDefinition(Method arg1) {
		Field annotation = getFieldAnnotation(arg1);
		if(annotation!=null) {
			InvocationDefinition def = new InvocationDefinition();
			def.fieldName = annotation.value();
			def.isGetter = arg1.getName().startsWith(GETTER_PREFIX);
			def.isSetter = arg1.getName().startsWith(SETTER_PREFIX);
			return def;
		}
		return null;
	}

	private Field getFieldAnnotation(Method arg1) {
		Field field = getFieldAnnotationFrom(arg1);
		if (field!=null) {
			return field;
		}
		
		// perhaps this is a getter/setter where the other one has the
		// annotation
		if (arg1.getName().startsWith(GETTER_PREFIX)) {
			return getFieldAnnotationForRePrefixed(arg1, SETTER_PREFIX);
		}
		if (arg1.getName().startsWith(SETTER_PREFIX)) {
			return getFieldAnnotationForRePrefixed(arg1, GETTER_PREFIX);
		}
		
		return null;
	}

	private Field getFieldAnnotationFrom(Method arg1) {
		if (arg1.isAnnotationPresent(Field.class)) {
			return arg1.getAnnotation(Field.class);
		}
		return null;
	}

	private Field getFieldAnnotationForRePrefixed(Method arg1, String otherPrefix) {
		Method[] methods = wrappedType.getMethods();
		String newName = otherPrefix + arg1.getName().substring(SETTER_PREFIX.length());
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
