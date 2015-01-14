package com.elsevier.vtw.core.model.wrapper.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

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
		// a request to wrap as a sub object properties of the parent
		boolean isNormalised;
		
		// the field name in the parent that is being turned into a property
		String fieldName;
		
		// is the method a getter/setter
		boolean isGetter;
		boolean isSetter;
		
		// what type and generic type does this invocation trade in for get/set
		Class<?> propertyType;
		Class<?> propertyGenericType;
		
		boolean isProperty() {
			return isGetter || isSetter;
		}
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
			if (definition.isProperty()){
				Property property = createProperty(definition);
				if (definition.isGetter) {
					return createGettingStrategy(property);
				} else if (definition.isSetter) {
					return createSettingStrategy(property);
				}
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
		Field fieldAnnotation = getAnnotation(Field.class, method);
		Normalised normalisedAnnotation = getAnnotation(Normalised.class, method);
		if(fieldAnnotation !=null || normalisedAnnotation!=null) {
			InvocationDefinition def = new InvocationDefinition();
			def.isNormalised = normalisedAnnotation!=null;
			def.fieldName = jsonFieldNameFrom(fieldAnnotation, method);
			def.isGetter = method.getName().startsWith(GETTER_PREFIX);
			def.isSetter = method.getName().startsWith(SETTER_PREFIX);
			if (def.isGetter) {
				def.propertyType = method.getReturnType();
				def.propertyGenericType = getGenericTypeFrom(method.getGenericReturnType());
			}
			if (def.isSetter){
				def.propertyType = method.getParameterTypes()[0];
				def.propertyGenericType = getGenericTypeFrom(method.getGenericParameterTypes()[0]);
			}
			return def;
		}
		return null;
	}

	private String jsonFieldNameFrom(Field fieldAnnotation, Method method) {
		String name = fieldAnnotation!=null ? fieldAnnotation.value() : null;
		
		if (name!=null && name.isEmpty()) {
			// if we can't find the field name from the method, then we're in trouble
			String propertyName = propertyNameFrom(method.getName());
			if (propertyName==null) {
				throw new UnsupportedOperationException("Cannot tag a non property with a Field attribute for inferring JSON field");
			}
			return propertyName;
		}
		
		return name;
	}

	private String propertyNameFrom(String methodName) {
		if (methodName.startsWith(SETTER_PREFIX) || methodName.startsWith(GETTER_PREFIX)) {
			String propertyName = methodName.substring(SETTER_PREFIX.length());
			if (propertyName.length()>0) {
				String firstCharacter = propertyName.substring(0,1);
				return firstCharacter.toLowerCase() + propertyName.substring(1);
			}
		}
		return null;
	}

	private Class<?> getGenericTypeFrom(Type genericType) {
		// Note: this is an awful hack but appears to work
		String typeName = lastItemFrom(genericType.toString().split("[<>]"));
		try {
			return Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			// no generic type found
			return null;
		}
	}

	private String lastItemFrom(String[] array) {
		if (array.length == 0) {
			return "";
		}
		return array[array.length-1];
	}

	/**
	 * Find the annotation on this method or its sibling if a getter/setter
	 * @param method the method to review
	 * @return annotation from this method, if available, or its sibling if not
	 */
	private <T extends Annotation> T getAnnotation(Class<T> annotationType, Method method) {
		T annotation = getAnnotationFrom(annotationType, method);
		if (annotation!=null) {
			return annotation;
		}
		
		// perhaps this is a getter/setter where the other one has the
		// annotation
		if (method.getName().startsWith(GETTER_PREFIX)) {
			return getAnnotationForRePrefixed(annotationType, method, SETTER_PREFIX);
		}
		if (method.getName().startsWith(SETTER_PREFIX)) {
			return getAnnotationForRePrefixed(annotationType, method, GETTER_PREFIX);
		}
		
		return null;
	}

	private <T extends Annotation> T getAnnotationFrom(Class<T> annotationType, Method method) {
		return method.getAnnotation(annotationType);
	}

	private <T extends Annotation> T getAnnotationForRePrefixed(Class<T> annotationType, Method method, String otherPrefix) {
		Method[] methods = wrappedType.getMethods();
		String newName = otherPrefix + method.getName().substring(SETTER_PREFIX.length());
		for(Method possibleMatch:methods) {
			if (possibleMatch.getName().equals(newName)) {
				return getAnnotationFrom(annotationType, possibleMatch);
			}
		}
		return null;
	}



	private HandlingStrategy createGettingStrategy(final Property property) {
		return new HandlingStrategy() {
			@Override
			public Object handle(Object[] parameters) {
				return property.get();
			}
		};
	}

	private HandlingStrategy createSettingStrategy(final Property property) {
		return new HandlingStrategy() {
			@Override
			public Object handle(Object[] parameters) {
				property.set(parameters[0]);
				return null;
			}
		};
	}
	

	private Property createProperty(InvocationDefinition definition) {
		if (definition.propertyType.equals(String.class)) {
			return new StringProperty(definition.fieldName, jsonData);
		} else if (definition.propertyType.equals(DateTime.class)){
			return new DateTimeProperty(definition.fieldName, jsonData);
		} else if (Wrapper.class.isAssignableFrom(definition.propertyType)) {
			return createWrapperProperty(definition);
		} else if (definition.propertyType.equals(ArrayWrapper.class)) {
			return new ArrayWrapperProperty(definition.propertyGenericType, definition.fieldName, jsonData);
		}
		throw new UnsupportedOperationException("Cannot deal with property of type " + definition.propertyType.getCanonicalName());
	}

	private Property createWrapperProperty(InvocationDefinition definition) {
		if (definition.isNormalised) {
			if (definition.isSetter) {
				throw new UnsupportedOperationException("Cannot set a normalised sub object");
			}
			return new NormalisingWrapperProperty(definition.propertyType, jsonData);
		} else {
			return new WrapperProperty(definition.propertyType, definition.fieldName, jsonData);
		}
	}

}
