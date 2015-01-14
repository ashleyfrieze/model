package com.elsevier.vtw.core.model.wrapper.internal;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Property {
	Object get();
	void set(Object value);
	void updateJsonRoot(ObjectNode root);
}
