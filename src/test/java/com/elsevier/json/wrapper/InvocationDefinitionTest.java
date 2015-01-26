package com.elsevier.json.wrapper;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.Test;

import com.elsevier.json.wrapper.InvocationDefinition;

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
