package com.elsevier.vtw.core.model.wrapper.internal;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.Test;

public class InvocationDefinitionTest {
	@Test
	public void pathProcessing() {
		InvocationDefinition def = new InvocationDefinition();
		def.fieldPath = new String[]{"a","b","c"};
		
		assertTrue(def.hasFieldPath());
		assertThat(def.getFieldName(), is("c"));
		
		assertThat(Arrays.asList(def.getPath()), contains("a","b"));
	}
}
