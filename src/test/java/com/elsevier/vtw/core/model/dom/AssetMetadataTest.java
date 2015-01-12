package com.elsevier.vtw.core.model.dom;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import com.elsevier.vtw.core.model.AssetMetadata;
import org.joda.time.DateTime;
import org.junit.Test;

public class AssetMetadataTest {
	
	@Test
	public void checkSimpleDataForAsset() {
		
		DateTime dateTime = new DateTime();
		
		AssetMetadata asset = AssetMetadata.builder()
								.id("id")
								.format("fo")
								.assetType("at")
								.version("ve")
								.filename("fi")
								.isFormatOf("if")
								.mode("mo")
								.isMultipart(false)
								.byteCount(1024)
								.sequence(1)
								.createdOn(dateTime)
								.build();
		
		assertThat("", asset.getId(), equalTo("id"));
		assertThat("", asset.getFormat(), equalTo("fo"));
		assertThat("", asset.getAssetType(), equalTo("at"));
		assertThat("", asset.getVersion(), equalTo("ve"));
		assertThat("", asset.getFilename(), equalTo("fi"));
		assertThat("", asset.getIsFormatOf(), equalTo("if"));
		assertThat("", asset.getMode(), equalTo("mo"));
		assertThat("", asset.isMultipart(), equalTo(false));
		assertThat("", asset.getByteCount(), equalTo(1024));
		assertThat("", asset.getSequence(), equalTo(1));
		assertThat("", asset.getCreatedOn().toString(), equalTo(dateTime.toString()));
		
	}

}
