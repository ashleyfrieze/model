package com.elsevier.vtw.core.model.wrapper;

import org.joda.time.DateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class ArrayWrapper<T> {
	private Class<T> type;
	private ArrayNode arrayNode;
	
	public ArrayWrapper(Class<T> type) {
		this(type, createArrayNode());
	}
	
	private static JsonNode createArrayNode() {
		JsonNodeFactory factory = JsonNodeFactory.instance;
		return new ArrayNode(factory);
	}

	public ArrayWrapper(Class<T> type, JsonNode arrayNode) {
		if (!(arrayNode instanceof ArrayNode)) {
			throw new RuntimeException("Cannot map array to non array node");
		}
		this.type = type;
		this.arrayNode = (ArrayNode)arrayNode;
	}
	
	public int size() {
		return arrayNode.size();
	}

	@SuppressWarnings("unchecked")
	public T get(int i) {
		if (i<0 || i>size()) {
			throw new IndexOutOfBoundsException();
		}
		JsonNode arrayValue = arrayNode.get(i);
		
		if (type.equals(String.class)) {
			// this cast can ONLY work in this situation as
			// T IS a String
			return (T)arrayValue.asText();
		} else if (DateTime.class.isAssignableFrom(type)) {
			return (T)new DateTime(arrayValue.asText());
		} else if (Integer.class.isAssignableFrom(type)) {
			return (T)(Integer)arrayValue.asInt();
		} else if (Double.class.isAssignableFrom(type)) {
			return (T)(Double)arrayValue.asDouble();
		} else if (Wrapper.class.isAssignableFrom(type)) {
			return (T) WrapperFactory.createUnchecked(type, (ObjectNode)arrayValue);
		}

		// we don't know how to have an ArrayWrapper that converts array elements to this
		throw new UnsupportedOperationException("Cannot provide conversion to type " + type);
	}

	/**
	 * Adds this array into the parent object
	 * @param target the new parent for this array
	 * @param fieldName the field within the parent
	 */
	public void insertInto(ObjectNode target, String fieldName) {
		target.set(fieldName, arrayNode);
	}

	public void add(T value) {
		if (type.equals(String.class)) {
			// this cast is context based
			arrayNode.add((String)value);
		} else if (DateTime.class.isAssignableFrom(type)) {
			arrayNode.add(DateTimeProperty.dateAsText((DateTime)value));
		} else if (Integer.class.isAssignableFrom(type)) {
			arrayNode.add((Integer)value);
		} else if (Double.class.isAssignableFrom(type)) {
			arrayNode.add((Double)value);
		} else if (Wrapper.class.isAssignableFrom(type)) {
			arrayNode.add(((Wrapper)value).json());
		} else {
			// we don't know how to have an ArrayWrapper that converts array elements to this
			throw new UnsupportedOperationException("Cannot provide conversion from type " + type);
		}
		
	}

	public void set(int i, T value) {
		if (type.equals(String.class)) {
			// this cast is context based
			arrayNode.set(i, new TextNode((String)value));
		} else {
			// we don't know how to have an ArrayWrapper that converts array elements to this
			throw new UnsupportedOperationException("Cannot provide conversion from type " + type);
		}
	}

}
