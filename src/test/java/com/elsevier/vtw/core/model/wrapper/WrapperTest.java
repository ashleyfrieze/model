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
		contentObject.id().set("myId");
		
		ObjectNode jsonNode = contentObject.json();
		assertTrue(jsonNode.toString().contains("\"@id\":\"myId\""));
	}
	
	@Test
	public void readWriteId() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		
		contentObject.id().set("myId");
		
		assertThat(contentObject.id().get(), is("myId"));
	}
	
	@Test
	public void roundTripSomeValues() {
		ContentObjectMetadata contentObject = WrapperFactory.create(ContentObjectMetadata.class);
		contentObject.id().set("myId");
		contentObject.ecmIdentifier().set("ecmID");
		contentObject.title().set("title");
		
		assertThat(contentObject.id().get(), is("myId"));
		assertThat(contentObject.ecmIdentifier().get(), is("ecmID"));
		assertThat(contentObject.title().get(), is("title"));
	}
}
