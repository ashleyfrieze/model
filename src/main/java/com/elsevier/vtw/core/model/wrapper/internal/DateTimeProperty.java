package com.elsevier.vtw.core.model.wrapper.internal;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DateTimeProperty extends BaseProperty implements Property {

	protected DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();
	
	public DateTimeProperty(String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
	}

	@Override
	public Object get() {
		return new DateTime(asText());
	}

	@Override
	public void set(Object value) {
		setAsText(dateFormatter.print((DateTime)value));		
	}

}
