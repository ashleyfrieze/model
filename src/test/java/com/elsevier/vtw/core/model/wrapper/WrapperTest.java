package com.elsevier.vtw.core.model.wrapper;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

import com.elsevier.vtw.core.model.wrapper.internal.WrapperFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.hamcrest.CoreMatchers.*;

public class WrapperTest {
	private static final String TEST_CO_DATE = "2014-11-10T12:10:55Z";
	private static final DateTime TEST_CO_DATE_TIME = new DateTime(TEST_CO_DATE);
	
	@Test
	public void writeId() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		contentObject.setId("myId");
		
		ObjectNode jsonNode = contentObject.json();
		assertTrue(jsonNode.toString().contains("\"@id\":\"myId\""));
	}
	
	@Test
	public void readWriteId() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		
		contentObject.setId("myId");
		
		assertThat(contentObject.getId(), is("myId"));
	}
	
	@Test
	public void roundTripSomeValues() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		contentObject.setId("myId");
		contentObject.setEcmIdentifier("ecmID");
		contentObject.setTitle("title");
		
		assertThat(contentObject.getId(), is("myId"));
		assertThat(contentObject.getEcmIdentifier(), is("ecmID"));
		assertThat(contentObject.getTitle(), is("title"));
	}
	
	@Test
	public void dateAsString() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		
		contentObject.setDateAsString(TEST_CO_DATE);
		assertThat(contentObject.getDateAsString(), is(TEST_CO_DATE));
	}
	
	@Test
	public void dateAsDate() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		
		contentObject.setDate(TEST_CO_DATE_TIME);
		assertThat(contentObject.getDate(), is(TEST_CO_DATE_TIME));
	}
	
	@Test
	public void canSetDateAsStringAndReadAsDate() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		
		contentObject.setDateAsString(TEST_CO_DATE);
		assertThat(contentObject.getDate(), is(TEST_CO_DATE_TIME));
	}
	
	
}
