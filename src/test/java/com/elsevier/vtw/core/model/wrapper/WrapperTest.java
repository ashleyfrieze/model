package com.elsevier.vtw.core.model.wrapper;

import static org.junit.Assert.*;

import org.junit.Test;

import com.elsevier.vtw.core.model.wrapper.internal.WrapperFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.hamcrest.CoreMatchers.*;

public class WrapperTest {
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
}
