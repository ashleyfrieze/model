package com.elsevier.vtw.core.model.wrapper;

import static org.junit.Assert.*;

import java.io.IOException;

import org.joda.time.DateTime;
import org.junit.Test;

import com.elsevier.vtw.core.model.wrapper.internal.ArrayWrapper;
import com.elsevier.vtw.core.model.wrapper.internal.WrapperFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Test
	public void defaultValueOfEmbeddedObjectIsNull() {
		Parent parent = WrapperFactory.create(Parent.class);
		
		assertNull(parent.getAssetMetadata());
	}
	
	@Test
	public void addEmbeddedObjectToParent() {
		Parent parent = WrapperFactory.create(Parent.class);
		parent.setAssetMetadata(WrapperFactory.create(AssetMetadata.class));
		
		assertNotNull(parent.getAssetMetadata());
	}
	
	@Test
	public void addEmbeddedObjectToParentAndRoundtripValuesInIt() {
		Parent parent = WrapperFactory.create(Parent.class);
		AssetMetadata asset = WrapperFactory.create(AssetMetadata.class);
		parent.setAssetMetadata(asset);
		
		asset.setTitle("Jingo");
		asset.setId("assetId");
		
		parent.setTitle("Bingo");
		parent.setId("parentId");
		
		assertTrue(parent.json().toString().contains("\"dct:title\":\"Jingo\""));
		assertTrue(parent.json().toString().contains("\"@id\":\"assetId\""));

		assertTrue(parent.json().toString().contains("\"dct:title\":\"Bingo\""));
		assertTrue(parent.json().toString().contains("\"@id\":\"parentId\""));
	}
	
	@Test
	public void readStringArray() throws JsonProcessingException, IOException {
		Parent parent = createCompoundTestObjectFromJson();
		assertThat(parent.getId(), is("myId"));
		
		assertThat(parent.getStringArray().size(), is(3));
		assertThat(parent.getStringArray().get(0), is("a"));
		assertThat(parent.getStringArray().get(1), is("b"));
		assertThat(parent.getStringArray().get(2), is("c"));
	}
	
	@Test
	public void readNullStringArrayResultsInNullReturn() {
		Parent parent = WrapperFactory.create(Parent.class);
		assertNull(parent.getStringArray());
	}
	
	@Test
	public void canSetStringArrayContents() {
		// start off with no string array
		Parent parent = WrapperFactory.create(Parent.class);
		assertNull(parent.getStringArray());
		
		// make a new one
		ArrayWrapper<String> newArray = new ArrayWrapper<String>(String.class);
		newArray.add("myString");
		
		parent.setStringArray(newArray);
		
		// and now the string array is not null and has a value in it
		assertNotNull(parent.getStringArray());
		assertThat(parent.getStringArray().get(0), is("myString"));
		
		// and it's in the json
		assertTrue(parent.json().toString().contains("myString"));
	}
	
	@Test
	public void canWriteStringArrayValues() throws JsonProcessingException, IOException {
		Parent parent = createCompoundTestObjectFromJson();
		
		assertThat(parent.getStringArray().get(0), is("a"));
		
		// setter
		parent.getStringArray().set(0, "99 red balloons");
		
		assertThat(parent.getStringArray().get(0), is("99 red balloons"));
	}
	
	@Test
	public void attachArrayWithOneObjectIn() {
		AssetMetadata asset = WrapperFactory.create(AssetMetadata.class);
		asset.setTitle("This is an asset");
		
		Parent parent = WrapperFactory.create(Parent.class);
		
		ArrayWrapper<AssetMetadata> assetArray = new ArrayWrapper<>(AssetMetadata.class);
		assetArray.add(asset);
		
		parent.setAssetArray(assetArray);
		
		// the json should now contain the title
		assertTrue(parent.json().toString().contains("is an asset"));
		
		// the title should be navigable
		assertThat(parent.getAssetArray().get(0).getTitle(), is("This is an asset"));
	}

	private Parent createCompoundTestObjectFromJson() throws IOException,
			JsonProcessingException {
		ObjectNode tree = treeFromJson("{\"@id\":\"myId\",\"stringarray\":[\"a\",\"b\",\"c\"]}");		
		Parent parent = WrapperFactory.create(Parent.class, tree);
		return parent;
	}
	
	private ObjectNode treeFromJson(String json) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode tree = (ObjectNode)mapper.readTree(json);
		return tree;
	}

	@Test
	public void canPretendPropertiesInRootAreSubObject() throws JsonProcessingException, IOException {
		String json = "{\"first\":\"Bill\", \"last\":\"Benson\", \"gender\":\"male\"}";
		Person person = WrapperFactory.create(Person.class, treeFromJson(json));
		
		assertThat(person.getFirstAndLastName().getFirstName(), is("Bill"));
		assertThat(person.getFirstAndLastName().getLastName(), is("Benson"));
		assertThat(person.getGender(), is("male"));
	}
}
