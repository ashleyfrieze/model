package com.elsevier.vtw.core.model.wrapper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class DateTimeProperty extends BaseProperty implements Property {

	protected static DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();
	
	public DateTimeProperty(String fieldName, ObjectNode jsonData) {
		super(fieldName, jsonData);
	}

	@Override
	public Object get() {
		return new DateTime(asText());
	}

	@Override
	public void set(Object value) {
		setAsText(dateAsText((DateTime)value));		
	}

	protected static String dateAsText(DateTime value) {
		return dateFormatter.print(value);
	}

}
