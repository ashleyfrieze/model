package com.elsevier.json.wrapper;

import static org.junit.Assert.*;

import java.io.IOException;

import org.joda.time.DateTime;
import org.junit.Test;

import com.elsevier.json.wrapper.ArrayWrapper;
import com.elsevier.json.wrapper.Field;
import com.elsevier.json.wrapper.Wrapper;
import com.elsevier.json.wrapper.WrapperFactory;
import com.elsevier.json.wrapper.teststructures.FirstAndLastName;
import com.elsevier.json.wrapper.teststructures.FlattenADeepSubObject;
import com.elsevier.json.wrapper.teststructures.FlattenedPerson;
import com.elsevier.json.wrapper.teststructures.NormalisedPerson;
import com.elsevier.json.wrapper.teststructures.PresentedProperties;
import com.elsevier.json.wrapper.teststructures.DefaultProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static org.hamcrest.Matchers.*;

public class WrapperTest {
	private static final String TEST_CO_DATE = "2014-11-10T12:10:55Z";
	private static final DateTime TEST_CO_DATE_TIME = new DateTime(TEST_CO_DATE);
	
	interface IdHolder extends Wrapper {
		@Field("@id")
		String getId();
		void setId(String id);
	}
	
	@Test
	public void writeId() {
		IdHolder contentObject = WrapperFactory.create(IdHolder.class);
		contentObject.setId("myId");
		
		assertJsonHasProperty(contentObject,"@id","myId");
	}

	@Test
	public void readWriteId() {
		IdHolder contentObject = WrapperFactory.create(IdHolder.class);
		
		contentObject.setId("myId");
		
		assertThat(contentObject.getId(), is("myId"));
	}
	
	@Test
	public void readNullString() {
		IdHolder contentObject = WrapperFactory.create(IdHolder.class);
		
		assertNull(contentObject.getId());
	}
	
	@Test
	public void toStringOutputsAsJson() {
		IdHolder contentObject = WrapperFactory.create(IdHolder.class);
		
		contentObject.setId("myId");
		assertThat(contentObject.toString(), is("{\"@id\":\"myId\"}"));
	}
	
	@Test
	public void equalsAndHashCodeViaValueIsPossible() {
		IdHolder contentObject1 = WrapperFactory.create(IdHolder.class);
		contentObject1.setId("myId");

		IdHolder contentObject2 = WrapperFactory.create(IdHolder.class);
		contentObject2.setId("myId");

		IdHolder contentObject3 = WrapperFactory.create(IdHolder.class);
		contentObject3.setId("hisId");
		
		assertEquals(contentObject1, contentObject2);
		assertNotEquals(contentObject2, contentObject3);
		
		assertEquals(contentObject1.hashCode(), contentObject2.hashCode());
		assertNotEquals(contentObject2.hashCode(), contentObject3.hashCode());
	}
	
	interface IdEcmIdTitle extends Wrapper {
		@Field("@id")
		String getId();
		void setId(String id);
		
		@Field("ecmId")
		String getEcmIdentifier();
		void setEcmIdentifier(String ecm);
		
		@Field("title")
		String getTitle();
		void setTitle(String title);
	}
	
	@Test
	public void roundTripSomeValues() {
		IdEcmIdTitle contentObject = WrapperFactory.create(IdEcmIdTitle.class);
		contentObject.setId("myId");
		contentObject.setEcmIdentifier("ecmID");
		contentObject.setTitle("title");
		
		assertThat(contentObject.getId(), is("myId"));
		assertThat(contentObject.getEcmIdentifier(), is("ecmID"));
		assertThat(contentObject.getTitle(), is("title"));
	}
	
	interface DateHolder extends Wrapper {
		@Field("date")
		String getDateAsString();
		void setDateAsString(String date);
		
		@Field("date")
		DateTime getDate();
		void setDate(DateTime dateTime);
	}
	
	@Test
	public void dateAsString() {
		DateHolder contentObject = WrapperFactory.create(DateHolder.class);
		
		contentObject.setDateAsString(TEST_CO_DATE);
		assertThat(contentObject.getDateAsString(), is(TEST_CO_DATE));
	}
	
	@Test
	public void dateAsDate() {
		DateHolder contentObject = WrapperFactory.create(DateHolder.class);
		
		contentObject.setDate(TEST_CO_DATE_TIME);
		assertThat(contentObject.getDate(), is(TEST_CO_DATE_TIME));
	}
	
	@Test
	public void canSetDateAsStringAndReadAsDate() {
		DateHolder contentObject = WrapperFactory.create(DateHolder.class);
		
		contentObject.setDateAsString(TEST_CO_DATE);
		assertThat(contentObject.getDate(), is(TEST_CO_DATE_TIME));
	}
	
	interface ArrayOfDatesHolder extends Wrapper {
		@Field("dateArray")
		ArrayWrapper<DateTime> getDateArray();
		void setDateArray(ArrayWrapper<DateTime> dateArray);
		
		// the duality of this string-based field means it can be done both ways
		@Field("dateArray")
		ArrayWrapper<String> getDateStringArray();
		void setDateStringArray(ArrayWrapper<String> dateArray);
	}
	
	@Test
	public void getReadWriteArrayOfDatesAsDateOrString() {
		ArrayOfDatesHolder holder = WrapperFactory.create(ArrayOfDatesHolder.class);
		ArrayWrapper<DateTime> dateArray = new ArrayWrapper<>(DateTime.class);
		
		holder.setDateArray(dateArray);
		
		dateArray.add(TEST_CO_DATE_TIME);
		
		assertThat(holder.getDateArray().get(0), is(TEST_CO_DATE_TIME));
		assertThat(holder.getDateStringArray().get(0), is(TEST_CO_DATE));
	}
	
	interface InferredFields extends Wrapper {
		@Field
		String getInferredField();
		void setInferredField(String value);
		
		@Field
		String getA();
		void setA(String a);
	}
	
	@Test
	public void fieldNameCanBeInferred() {
		InferredFields inferredFields = WrapperFactory.create(InferredFields.class);
		
		inferredFields.setInferredField("inferred");
		assertThat(inferredFields.getInferredField(), is("inferred"));
		
		assertJsonHasProperty(inferredFields, "inferredField", "inferred");
	}
	
	@Test
	public void fieldNameCanBeInferredOneCharacterName() {
		InferredFields inferredFields = WrapperFactory.create(InferredFields.class);
		
		inferredFields.setA("inferred");
		assertThat(inferredFields.getA(), is("inferred"));
		
		assertJsonHasProperty(inferredFields, "a", "inferred");
	}
	
	interface WithEmbeddedObject extends Wrapper {
		@Field
		EmbeddedObject getEmbeddedObject();
		void setEmbeddedObject(EmbeddedObject embedded);
	}
	
	interface EmbeddedObject extends Wrapper {
		@Field
		void setTitle(String title);
		String getTitle();
		
		@Field("@id")
		void setId(String id);
	}
	
	@Test
	public void defaultValueOfEmbeddedObjectIsNull() {
		WithEmbeddedObject parent = WrapperFactory.create(WithEmbeddedObject.class);
		
		assertNull(parent.getEmbeddedObject());
	}
	
	@Test
	public void addEmbeddedObjectToParent() {
		WithEmbeddedObject parent = WrapperFactory.create(WithEmbeddedObject.class);
		parent.setEmbeddedObject(WrapperFactory.create(EmbeddedObject.class));
		
		assertNotNull(parent.getEmbeddedObject());
	}
	
	@Test
	public void addEmbeddedObjectToParentAndRoundtripValuesInIt() {
		WithEmbeddedObject parent = WrapperFactory.create(WithEmbeddedObject.class);
		EmbeddedObject asset = WrapperFactory.create(EmbeddedObject.class);
		parent.setEmbeddedObject(asset);
		
		asset.setTitle("Jingo");
		asset.setId("assetId");
		
		assertJsonHasProperty(parent, "title", "Jingo");
		assertJsonHasProperty(parent, "@id", "assetId");
	}
	
	
	interface IdAndArrays extends Wrapper {
		@Field("@id")
		void setId(String id);
		String getId();
		
		@Field
		void setStringArray(ArrayWrapper<String> stringArray);
		ArrayWrapper<String> getStringArray();
		
		@Field("embeddedArray")
		void setEmbeddedObjectArray(ArrayWrapper<EmbeddedObject> array);
		ArrayWrapper<EmbeddedObject> getEmbeddedObjectArray();
	}
	
	@Test
	public void readStringArray() throws JsonProcessingException, IOException {
		IdAndArrays parent = createCompoundTestObjectFromJson();
		assertThat(parent.getId(), is("myId"));
		
		assertThat(parent.getStringArray().size(), is(3));
		assertThat(parent.getStringArray().get(0), is("a"));
		assertThat(parent.getStringArray().get(1), is("b"));
		assertThat(parent.getStringArray().get(2), is("c"));
	}
	
	@Test
	public void readNullStringArrayResultsInNullReturn() {
		IdAndArrays parent = WrapperFactory.create(IdAndArrays.class);
		assertNull(parent.getStringArray());
	}
	
	@Test
	public void canSetStringArrayContents() {
		// start off with no string array
		IdAndArrays parent = WrapperFactory.create(IdAndArrays.class);
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
		IdAndArrays parent = createCompoundTestObjectFromJson();
		
		assertThat(parent.getStringArray().get(0), is("a"));
		
		// setter
		parent.getStringArray().set(0, "99 red balloons");
		
		assertThat(parent.getStringArray().get(0), is("99 red balloons"));
	}
	
	@Test
	public void attachArrayWithOneObjectIn() {
		EmbeddedObject asset = WrapperFactory.create(EmbeddedObject.class);
		asset.setTitle("This is an asset");
		
		IdAndArrays parent = WrapperFactory.create(IdAndArrays.class);
		
		ArrayWrapper<EmbeddedObject> assetArray = new ArrayWrapper<>(EmbeddedObject.class);
		assetArray.add(asset);
		
		parent.setEmbeddedObjectArray(assetArray);
		
		// the json should now contain the title
		assertTrue(parent.json().toString().contains("is an asset"));
		
		// the title should be navigable
		assertThat(parent.getEmbeddedObjectArray().get(0).getTitle(), is("This is an asset"));
	}

	private IdAndArrays createCompoundTestObjectFromJson() throws IOException,
			JsonProcessingException {
		ObjectNode tree = treeFromJson("{\"@id\":\"myId\",\"stringArray\":[\"a\",\"b\",\"c\"]}");		
		return WrapperFactory.create(IdAndArrays.class, tree);
	}
	
	static ObjectNode treeFromJson(String json) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode tree = (ObjectNode)mapper.readTree(json);
		return tree;
	}

	@Test
	public void canPretendPropertiesInRootAreSubObject() throws JsonProcessingException, IOException {
		String json = "{\"first\":\"Bill\", \"last\":\"Benson\", \"gender\":\"male\"}";
		NormalisedPerson person = WrapperFactory.create(NormalisedPerson.class, treeFromJson(json));
		
		assertThat(person.getFirstAndLastName().getFirstName(), is("Bill"));
		assertThat(person.getFirstAndLastName().getLastName(), is("Benson"));
		assertThat(person.getGender(), is("male"));
	}

	@Test
	public void gettingFlattenedProperties() {
		FirstAndLastName firstAndLast = WrapperFactory.create(FirstAndLastName.class);
		firstAndLast.setFirstName("Charlie");
		firstAndLast.setLastName("Brown");

		FlattenedPerson person = WrapperFactory.create(FlattenedPerson.class);
		person.setFirstAndLast(firstAndLast);
		
		// demonstrate that the set of the nested object is visible to the flattened getters
		assertThat(person.getFirstName(), is("Charlie"));
		assertThat(person.getLastName(), is("Brown"));
	}
	
	@Test
	public void settingFlattenedProperties() {
		FlattenedPerson person = WrapperFactory.create(FlattenedPerson.class);
		person.setFirstName("Charlie");
		person.setLastName("Brown");
		
		FirstAndLastName firstAndLast = person.getFirstAndLast();
		
		// demonstrate that the set applied to the nested object via either getter
		assertThat(firstAndLast.getFirstName(), is("Charlie"));
		assertThat(person.getFirstName(), is("Charlie"));
		assertThat(firstAndLast.getLastName(), is("Brown"));
		assertThat(person.getLastName(), is("Brown"));
	}
	
	@Test
	public void canFlattenADeepObject() {
		FlattenADeepSubObject flatten = WrapperFactory.create(FlattenADeepSubObject.class);
		
		assertNull(flatten.getFirstAndLast());
		
		FirstAndLastName firstAndLast = WrapperFactory.create(FirstAndLastName.class);
		firstAndLast.setFirstName("Boris");
		firstAndLast.setLastName("Becker");
		
		flatten.setFirstAndLast(firstAndLast);
		
		// now test this worked
		assertThat(flatten.getFirstAndLast().getFirstName(), is("Boris"));
		assertThat(flatten.getFirstAndLast().getLastName(), is("Becker"));
		
		assertThat(flatten.json().toString(), is("{\"a\":{\"b\":{\"c\":{\"first\":\"Boris\",\"last\":\"Becker\"}}}}"));
	}
	
	@Test
	public void compositePropertyReadFromActualProperties() {
		PresentedProperties obj = WrapperFactory.create(PresentedProperties.class);
		
		obj.setFirstName("Bill");
		obj.setLastName("Bryson");
		
		assertThat(obj.getFullName(), is("Bill Bryson"));
	}
	
	@Test
	public void compositePropertyWritesToActualProperties() {
		PresentedProperties obj = WrapperFactory.create(PresentedProperties.class);
		
		obj.setFullName("Bill Bailey");
		
		assertThat(obj.getFirstName(), is("Bill"));
		assertThat(obj.getLastName(), is("Bailey"));
	}
	
	@Test
	public void defaultPropertyReadFromActualProperties() {
		DefaultProperties obj = WrapperFactory.create(DefaultProperties.class);

		obj.setFirstName("Bill");
		obj.setLastName("Bryson");

		assertThat(obj.getFullName(), is("Bill Bryson"));
	}

	@Test
	public void defaultPropertyWritesToActualProperties() {
		PresentedProperties obj = WrapperFactory.create(PresentedProperties.class);

		obj.setFullName("Bill Bailey");

		assertThat(obj.getFirstName(), is("Bill"));
		assertThat(obj.getLastName(), is("Bailey"));
	}

	interface NumberContainer extends Wrapper {
		@Field
		Integer getInteger();
		void setInteger(Integer number);
		
		@Field
		Double getDouble();
		void setDouble(Double number);
	}
	
	@Test
	public void readWriteIntegerProperty() {
		NumberContainer container = WrapperFactory.create(NumberContainer.class);
		container.setInteger(123);

		assertThat(container.getInteger(), is(123));
		
		assertJsonHas(container, "integer", 123);
	}
	
	@Test
	public void readWriteDoubleProperty() {
		NumberContainer container = WrapperFactory.create(NumberContainer.class);
		container.setDouble(123.45);

		assertThat(container.getDouble(), is(123.45));
		
		assertJsonHas(container, "double", 123.45);
	}
	
	interface NumberArrayContainer extends Wrapper {
		@Field
		ArrayWrapper<Integer> getIntegerArray();
		void setIntegerArray(ArrayWrapper<Integer> array);
		
		@Field
		ArrayWrapper<Double> getDoubleArray();
		void setDoubleArray(ArrayWrapper<Double> array);
	}
	
	@Test
	public void readWriteIntegerArray() {
		NumberArrayContainer container = WrapperFactory.create(NumberArrayContainer.class);
		ArrayWrapper<Integer> array = new ArrayWrapper<Integer>(Integer.class);

		container.setIntegerArray(array);
		array.add(123);
		
		assertThat(container.getIntegerArray().get(0), is(123));
		
		assertJsonHas(container, "integerArray", "[123]");
	}
	
	@Test
	public void readWriteDoubleArray() {
		NumberArrayContainer container = WrapperFactory.create(NumberArrayContainer.class);
		ArrayWrapper<Double> array = new ArrayWrapper<Double>(Double.class);

		container.setDoubleArray(array);
		array.add(123.45);
		
		assertThat(container.getDoubleArray().get(0), is(123.45));
		
		assertJsonHas(container, "doubleArray", "[123.45]");
	}

	
	/**
	 * Quoted value
	 */
	private void assertJsonHasProperty(Wrapper wrapper, String name,
			String value) {
		ObjectNode jsonNode = wrapper.json();
		assertTrue(jsonNode.toString().contains("\""+name+"\":\""+value+"\""));
	}
	
	/**
	 * Unquoted values
	 */
	private void assertJsonHas(Wrapper wrapper, String name,
			Object value) {
		ObjectNode jsonNode = wrapper.json();
		assertTrue(jsonNode.toString().contains("\""+name+"\":"+value));
	}
}
