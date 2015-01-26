package com.elsevier.vtw.core.model.wrapper;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


public class PerformanceTest {
	private String exampleJson = "{"
			+ " \"@id\": \"myId\","
			+ " \"child\": { "
			+ "    \"title\" : \"childTitle\","
			+ "    \"count\" : 27 "
			+ "  },"
			+ " \"array\" : ["
			+ "     \"one\",\"two\",\"three\"  "
			+ " ] "
			+ "}";
	
	interface Parent extends Wrapper {
		@Field("@id")
		String getId();
		
		@Field("child")
		Child getChild();
		
		@Field("array")
		ArrayWrapper<String> getArray();
	}
	
	interface Child extends Wrapper {
		@Field("title")
		String getTitle();
		
		@Field("count")
		Integer getCount();
	}
	
	static class ParentPojo {
		private String id;
		private String[] array;
		private ChildPojo child;
		
		@JsonGetter("@id")
		public String getId() {
			return id;
		}
		@JsonSetter("@id")
		public void setId(String id) {
			this.id = id;
		}
		public String[] getArray() {
			return array;
		}
		public void setArray(String[] array) {
			this.array = array;
		}
		public ChildPojo getChild() {
			return child;
		}
		public void setChild(ChildPojo child) {
			this.child = child;
		}
	}
	
	static class ChildPojo {
		private String title;
		private int count;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
	}
	
	private static int numberOfIterations=30000;
	
	@Test
	public void aAAAFirstTest() throws JsonProcessingException, IOException {
		// here purely to take the test set up time
		ObjectNode root = WrapperTest.treeFromJson(exampleJson);
	}
	
	@Test
	public void directJsonPerformance() throws JsonProcessingException, IOException {
		for(int i=0; i<numberOfIterations; i++) {
			ObjectNode root = WrapperTest.treeFromJson(exampleJson);

			assertThat(root.get("@id").asText(), is("myId"));

			JsonNode childNode = root.get("child");
			if (!childNode.isObject()) {
				throw new RuntimeException("not a child object");
			}
			ObjectNode child = (ObjectNode)childNode;
			assertThat(child.get("title").asText(), is("childTitle"));
			assertThat(child.get("count").asInt(), is(27));
			
			JsonNode arrayNode = root.get("array");
			if (!arrayNode.isArray()) {
				throw new RuntimeException("not array");
			}
			ArrayNode array = (ArrayNode)arrayNode;
			assertThat(array.get(0).asText(), is("one"));
			assertThat(array.get(1).asText(), is("two"));
			assertThat(array.get(2).asText(), is("three"));
		}
	}
	
	@Test
	public void wrapperPerformance() throws JsonProcessingException, IOException {
		for(int i=0; i<numberOfIterations; i++) {
			ObjectNode root = WrapperTest.treeFromJson(exampleJson);
			Parent parent = WrapperFactory.create(Parent.class, root);
			
			assertThat(parent.getId(), is("myId"));
			
			Child child = parent.getChild();

			assertThat(child.getTitle(), is("childTitle"));
			assertThat(child.getCount(), is(27));
			
			ArrayWrapper<String> array = parent.getArray();
			
			assertThat(array.get(0), is("one"));
			assertThat(array.get(1), is("two"));
			assertThat(array.get(2), is("three"));
		}
	}
	
	@Test
	public void pojoPerformance() throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		for(int i=0; i<numberOfIterations; i++) {
			ParentPojo parent = mapper.readValue(exampleJson, ParentPojo.class);
			
			assertThat(parent.getId(), is("myId"));
			
			ChildPojo child = parent.getChild();

			assertThat(child.getTitle(), is("childTitle"));
			assertThat(child.getCount(), is(27));
			
			String[] array = parent.getArray();
			
			assertThat(array[0], is("one"));
			assertThat(array[1], is("two"));
			assertThat(array[2], is("three"));
		}
	}
}
