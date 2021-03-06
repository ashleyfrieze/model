package com.elsevier.vtw.core.model.dom.transform;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

abstract public class AbstractTransformImpl {
	
	protected static final JsonNodeFactory FACTORY = JsonNodeFactory.instance;

	protected static final ObjectMapper MAPPER = new ObjectMapper();

	protected DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();
	
	protected static String safeString(ObjectNode jsonValue, String fieldName) {

		JsonNode node = jsonValue.findValue(fieldName);
		if (node == null) {
			return null;
		}

		return node.textValue();
	}
	
	protected static String[] safeStringArray(ObjectNode jsonValue, String fieldName) {
		JsonNode node = jsonValue.findValue(fieldName);
		if (node == null || !node.isArray()) {
			return new String[0];
		}
		
		String[] result = new String[node.size()];
		for(int i=0; i<node.size(); i++) {
			result[i] = node.get(i).textValue();
		}
		return result;
	}
	
	protected Integer safeInt(ObjectNode jsonValue, String fieldName) {
		IntNode node = (IntNode) jsonValue.findValue(fieldName);
		if (node == null) {
			return null;
		}

		return node.asInt();
	}


}
