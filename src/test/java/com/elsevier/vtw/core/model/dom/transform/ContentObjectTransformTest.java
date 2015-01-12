package com.elsevier.vtw.core.model.dom.transform;

import com.elsevier.vtw.core.model.dom.AssetMetadata;
import com.elsevier.vtw.core.model.dom.ContentObjectMetadata;
import com.elsevier.vtw.core.model.dom.GenerationMetadata;
import com.elsevier.vtw.core.model.dom.factory.ContentObjectMetadataFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.util.List;

import static com.elsevier.vtw.core.model.dom.factory.ContentObjectMetadataFactory.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class ContentObjectTransformTest {
	
	private static final DefaultCOMetadataTransformImpl CO_TRANSFORMER = new DefaultCOMetadataTransformImpl();

	private DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();

	@Test
	public void roundTripSimpleContentObject() {
		
		ContentObjectMetadata com = ContentObjectMetadataFactory.assetlessContentObjectMetadata();
		
		ObjectNode coNode = com.toJson(CO_TRANSFORMER);
		
		String jsonString = coNode.toString();		
		assertThat("not null", jsonString, notNullValue());
		
		ContentObjectMetadata com2 = CO_TRANSFORMER.transformFromJson(coNode);
		
		assertThat(com2.getId(), equalTo(TEST_CO_ID));
		assertThat(com2.getEcmIdentifier(), equalTo(TEST_CO_ECM_ID));
		assertThat(com2.getTitle(), equalTo(TEST_CO_TITLE));
		assertThat(com2.getDctType(), equalTo(TEST_CO_TYPE));
		assertThat(com2.getAggregationType().get(0), equalTo(TEST_CO_AGG_TYPE));
		assertThat(dateFormatter.print(com2.getDate()), equalTo(TEST_CO_DATE));
		
	}
	
	@Test
	public void roundTripCompletedMultipartContentObject() {
		
		ContentObjectMetadata com = ContentObjectMetadataFactory.completedMultipartContentObject();
		
		ObjectNode coNode = com.toJson(CO_TRANSFORMER);
		
		String jsonString = coNode.toString();
		assertThat(jsonString, notNullValue());
		
		ContentObjectMetadata com2 = CO_TRANSFORMER.transformFromJson(coNode);
		
		assertThat(com2.getId(), equalTo(TEST_CO_ID));
		assertThat(com2.getEcmIdentifier(), equalTo(TEST_CO_ECM_ID));
		assertThat(com2.getTitle(), equalTo(TEST_CO_TITLE));
		assertThat(com2.getDctType(), equalTo(TEST_CO_TYPE));
		assertThat(com2.getAggregationType().get(0), equalTo(TEST_CO_AGG_TYPE));
		assertThat(dateFormatter.print(com2.getDate()), equalTo(TEST_CO_DATE));
		
		validateAssetMetadata(com2);
		
		List<GenerationMetadata> generations = com2.getGenerationMetadata();
		assertNotNull(generations);
		assertThat(generations.size(), equalTo(1));
		
		GenerationMetadata generation = generations.get(0);
		assertThat(generation.getClassificationLevel(), equalTo(TEST_GEN_CLASSIFICATION_LEVEL));
		assertThat(generation.getStage(), equalTo(TEST_GEN_STAGE));
		assertThat(generation.getIsGenerationOf(), equalTo(TEST_GEN_IS_GENERATION_OF));
		assertThat(generation.getAssets().size(), equalTo(3));
		
	}

	private void validateAssetMetadata(ContentObjectMetadata com2) {
		List<AssetMetadata> assets = com2.getAssetMetadata();
		assertNotNull(assets);
		assertThat(assets.size(), equalTo(3));
		
		AssetMetadata asset1 = assets.get(0);
		assertThat(asset1.getId(), equalTo(TEST_ASSET_1_ID));
		assertThat(asset1.getAssetType(), equalTo(TEST_ASSET_1_TYPE));
		assertThat(asset1.getFormat(), equalTo(TEST_ASSET_1_FORMAT));
		assertThat(asset1.getVersion(),equalTo(TEST_ASSET_1_VERSION));
		assertThat(asset1.getFilename(), equalTo(TEST_ASSET_1_FILENAME));
		assertThat(asset1.getByteCount(), equalTo(TEST_ASSET_1_BYTECOUNT));
		assertThat(asset1.getIsFormatOf(), equalTo(TEST_ASSET_1_IS_FORMAT_OF));
		assertThat(asset1.getMode(), equalTo(TEST_ASSET_1_MODE));
		assertThat(dateFormatter.print(asset1.getCreatedOn()), equalTo(TEST_ASSET_1_CREATED));
		assertThat(asset1.getSequence(), equalTo(TEST_ASSET_1_SEQUENCE_NO));
		
		AssetMetadata asset2 = assets.get(1);
		assertThat(asset2.getId(), equalTo(TEST_ASSET_2_ID));
		assertThat(asset2.getAssetType(), equalTo(TEST_ASSET_2_TYPE));
		assertThat(asset2.getFormat(), equalTo(TEST_ASSET_2_FORMAT));
		assertThat(asset2.getVersion(),equalTo(TEST_ASSET_2_VERSION));
		assertThat(asset2.getFilename(), equalTo(TEST_ASSET_2_FILENAME));
		assertThat(asset2.getByteCount(), equalTo(TEST_ASSET_2_BYTECOUNT));
		assertThat(asset2.getIsFormatOf(), equalTo(TEST_ASSET_2_IS_FORMAT_OF));
		assertThat(asset2.getMode(), equalTo(TEST_ASSET_2_MODE));
		assertThat(dateFormatter.print(asset2.getCreatedOn()), equalTo(TEST_ASSET_2_CREATED));
		
		AssetMetadata asset3 = assets.get(2);
		assertThat(asset3.getId(), equalTo(TEST_ASSET_3_ID));
		assertThat(asset3.getAssetType(), equalTo(TEST_ASSET_3_TYPE));
		assertThat(asset3.getFormat(), equalTo(TEST_ASSET_3_FORMAT));
		assertThat(asset3.getVersion(),equalTo(TEST_ASSET_3_VERSION));
		assertThat(asset3.getFilename(), equalTo(TEST_ASSET_3_FILENAME));
		assertThat(asset3.getByteCount(), equalTo(TEST_ASSET_3_BYTECOUNT));
		assertThat(asset3.getIsFormatOf(), equalTo(TEST_ASSET_3_IS_FORMAT_OF));
		assertThat(asset3.getMode(), equalTo(TEST_ASSET_3_MODE));
		assertThat(dateFormatter.print(asset3.getCreatedOn()), equalTo(TEST_ASSET_3_CREATED));
	}

}
