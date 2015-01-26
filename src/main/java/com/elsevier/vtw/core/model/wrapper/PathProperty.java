package com.elsevier.vtw.core.model.wrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Decorator of a property that allows for a path to be cut through the json to the
 * actual property
 */
public class PathProperty implements Property {
	private String[] path;
	private Property decorated;
	private ObjectNode rootNode;
	
	public PathProperty(ObjectNode rootNode, Property decorated, String[] path) {
		this.rootNode = rootNode;
		this.decorated = decorated;
		this.path = path;
	}

	@Override
	public Object get() {
		// iterate over the path to find the real node to access
		ObjectNode current = rootNode;
		for(String subProperty:path) {
			JsonNode next = current.get(subProperty);
			
			// can't find the node in the path
			if (next==null || !next.isObject()) {
				return null;
			}
			current = (ObjectNode)next;
		}
		
		// now update the decorated
		decorated.updateJsonRoot(current);
		
		// the property node we're decorating is now in the right place for this to work
		return decorated.get();
	}

	@Override
	public void set(Object value) {
		// iterate over the path, creating parents if necessary
		// iterate over the path to find the real node to access
		ObjectNode current = rootNode;
		for(String subProperty:path) {
			ObjectNode next = ensureNextNodeExistsAndIsObjectNode(current, subProperty);

			current = next;
		}
		
		// now update the decorated
		decorated.updateJsonRoot(current);
		
		// the property node we're decorating is now in the right place for this to work
		decorated.set(value);
		
	}

	private ObjectNode ensureNextNodeExistsAndIsObjectNode(ObjectNode current, String subProperty) {
		JsonNode subNode = current.get(subProperty);
		
		// create one if necessary
		if (subNode == null) {
			ObjectNode newChild = new ObjectNode(JsonNodeFactory.instance);
			current.set(subProperty, newChild);
			return newChild;
		}
		
		if (subNode instanceof ObjectNode) {
			return (ObjectNode)subNode;
		}
		
		throw new UnsupportedOperationException("Cannot iterate into the sub node " + subProperty + " as it is not an object node");
	}

	@Override
	public void updateJsonRoot(ObjectNode root) {
		rootNode = root;
	}

}
