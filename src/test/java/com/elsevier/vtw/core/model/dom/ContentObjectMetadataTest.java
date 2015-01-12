package com.elsevier.vtw.core.model.dom;

import com.elsevier.vtw.core.model.AssetMetadata;
import com.elsevier.vtw.core.model.ContentObjectMetadata;
import com.elsevier.vtw.core.model.GenerationMetadata;
import com.elsevier.vtw.core.model.dom.factory.ContentObjectMetadataFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

import java.util.List;

import static com.elsevier.vtw.core.model.dom.factory.ContentObjectMetadataFactory.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ContentObjectMetadataTest {
	
	private DateTimeFormatter dateFormatter = ISODateTimeFormat.dateTimeNoMillis().withZoneUTC();

	@Test
	public void checkSimpleDataForContentObject() throws Exception {
		
		ContentObjectMetadata md = ContentObjectMetadataFactory.assetlessContentObjectMetadata();
		
		assertThat("The id should be 'id'", md.getId(), 
					equalTo(TEST_CO_ID));
		assertThat("The Aggregation Type should contain 1 entry", md.getAggregationType().size(),
					equalTo(1));
		assertThat("The Aggregation Type entry should be 'at'", md.getAggregationType().get(0), 
					equalTo(TEST_CO_AGG_TYPE));
		assertThat("The ecm identifier should be 'ei'", md.getEcmIdentifier(), 
					equalTo(TEST_CO_ECM_ID));
		assertThat("The title should be 'ti'", md.getTitle(), 
					equalTo(TEST_CO_TITLE));
		assertThat("The dctType should be 'ty'", md.getDctType(),
					equalTo(TEST_CO_TYPE));
		assertThat("The date should be now", dateFormatter.print(md.getDate()), 
					equalTo(TEST_CO_DATE));
		
		
		assertThat("The Asset Metadata should be empty", md.getAssetMetadata().size(), equalTo(0));
		assertThat("The Generation Data should be empty", md.getGenerationMetadata().size(), equalTo(0));
			
		
	}
	
	@Test
	public void checkSimpleCOAndAsset() throws Exception {
		DateTime dateTime = new DateTime();
		
		ContentObjectMetadata md = ContentObjectMetadata.builder()
										.id("id")
										.ecmIdentifier("ei")
										.aggregationType("at")
										.title("ti")
										.dctType("ty")
										.date(dateTime)
										.withAssetMetadata()
											.id("aid")
											.format("afo")
											.assetType("aas")
											.version("ave")
											.filename("afi")
											.isFormatOf("aifo")
											.byteCount(2048)
											.isMultipart(false)
											.createdOn(dateTime)
											.insert()
										.build();
		
		assertThat("metadata should not be null", md.getAssetMetadata(), notNullValue());
		assertThat("metadata should contain one value", md.getAssetMetadata().size(), equalTo(1));
		
		AssetMetadata asset = md.getAssetMetadata().get(0);
		
		assertThat("asset id should be 'aid'", asset.getId(), equalTo("aid"));
	}
	
	@Test
	public void checkSimpleCOAndGeneration() throws Exception {
		
		DateTime dateTime = new DateTime();
		
		ContentObjectMetadata md = ContentObjectMetadata.builder()
										.id("id")
										.ecmIdentifier("eid")
										.aggregationType("ag")
										.title("ti")
										.dctType("ty")
										.date(dateTime)
										.withGenerationMetadata()
											.classificationLevel("cl")
											.stage("st")
											.isGenerationOf("id")
											.withAsset("id")
											.insert()
										.build();
		
		List<GenerationMetadata> gms = md.getGenerationMetadata();
		assertThat("generation md should not be null", gms, notNullValue());
		assertThat("generation md should contain oen value", gms.size(), equalTo(1));
		
		GenerationMetadata gm = gms.get(0);
		
		assertThat("gm classification should be 'cl'", gm.getClassificationLevel(), equalTo("cl"));
		
	}

}
