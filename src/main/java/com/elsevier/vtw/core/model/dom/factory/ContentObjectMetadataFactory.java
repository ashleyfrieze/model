package com.elsevier.vtw.core.model.dom.factory;

import org.joda.time.DateTime;

import com.elsevier.vtw.core.model.dom.ContentObjectMetadata;

public class ContentObjectMetadataFactory {
	
	public static final String TEST_CO_DATE = "2014-11-10T12:10:55Z";
	public static final String TEST_CO_AGG_TYPE = "http://data.elsevier.com/vocabulary/ElsevierContentTypes/1";
	public static final String TEST_CO_ECM_ID = "pii:S0140673602689334";
	public static final String TEST_CO_ID = "http://vtw.elsevier.com/content/pii/S0140673602689334";
	public static final String TEST_CO_TYPE = "http://data.elsevier.com/vocabulary/ElsevierContentTypes/1.1";
	public static final String TEST_CO_TITLE = "A QUIVER FULL OF THEM.";
	
	public static final String TEST_ASSET_1_ID = "https://s3-eu-west-1.amazonaws.com/dev-vtw-bucket/testUpload/jill.txt";
	public static final String TEST_ASSET_1_FORMAT = "text/plain";
	public static final String TEST_ASSET_1_TYPE = "AUTH-ORG";
	public static final String TEST_ASSET_1_VERSION = "161ef8c92c357995da942c567fcffd19";
	public static final int TEST_ASSET_1_BYTECOUNT = 19;
	public static final String TEST_ASSET_1_FILENAME = "jill.txt";
	public static final String TEST_ASSET_1_IS_FORMAT_OF = "http://vtw.elsevier.com/content/pii/S0140673602689334";
	public static final String TEST_ASSET_1_MODE = "private";
	public static final boolean TEST_ASSET_1_IS_MULTIPART = true;
	public static final String TEST_ASSET_1_CREATED = "2014-11-10T12:10:55Z";
	public static final int TEST_ASSET_1_SEQUENCE_NO = 1;
	
	public static final String TEST_ASSET_2_ID = "https://s3-eu-west-1.amazonaws.com/dev-vtw-bucket/testUpload/jack.txt";
	public static final String TEST_ASSET_2_FORMAT = "text/plain";
	public static final String TEST_ASSET_2_TYPE = "AUTH-ORG";
	public static final String TEST_ASSET_2_VERSION = "e6afe92f5ff0fda96392c9ee7ec5721e";
	public static final int TEST_ASSET_2_BYTECOUNT = 18;
	public static final String TEST_ASSET_2_FILENAME = "jill.txt";
	public static final String TEST_ASSET_2_IS_FORMAT_OF = "http://vtw.elsevier.com/content/pii/S0140673602689334";
	public static final String TEST_ASSET_2_MODE = "private";
	public static final boolean TEST_ASSET_2_IS_MULTIPART = true;
	public static final String TEST_ASSET_2_CREATED = "2014-11-10T12:10:55Z";
	public static final int TEST_ASSET_2_SEQUENCE_NO = 2;
	
	public static final String TEST_ASSET_3_ID = "https://s3-eu-west-1.amazonaws.com/dev-vtw-bucket/testUpload/jack.txt";
	public static final String TEST_ASSET_3_FORMAT = null;
	public static final String TEST_ASSET_3_TYPE = "AUTH-ORG";
	public static final String TEST_ASSET_3_VERSION = "0";
	public static final int TEST_ASSET_3_BYTECOUNT = 0;
	public static final String TEST_ASSET_3_FILENAME = null;
	public static final String TEST_ASSET_3_IS_FORMAT_OF = null;
	public static final String TEST_ASSET_3_MODE = "private";
	public static final boolean TEST_ASSET_3_IS_MULTIPART = true;
	public static final String TEST_ASSET_3_CREATED = "2014-11-10T12:10:55Z";
	public static final int TEST_ASSET_3_SEQUENCE_NO = 3;
	
	public static final String TEST_GEN_CLASSIFICATION_LEVEL = "public";
	public static final String TEST_GEN_STAGE = "corrected_proof";
	public static final String TEST_GEN_IS_GENERATION_OF = "http://vtw.elsevier.com/content/pii/S0140673602689334";
	public static final String TEST_GEN_ASSET_1 = "https://s3-eu-west-1.amazonaws.com/dev-vtw-bucket/testUpload/jack.txt";
	public static final String TEST_GEN_ASSET_2 = "https://s3-eu-west-1.amazonaws.com/dev-vtw-bucket/testUpload/jill.txt";
	public static final String TEST_GEN_ASSET_3	 = "http://vtw.elsevier.com/asset/pii/S0140673602689334?dctType=AUTH-ORG&fmt=0&ver=0";
	

	public static ContentObjectMetadata assetlessContentObjectMetadata() {

		return new ContentObjectMetadata.Builder()
										.title(TEST_CO_TITLE)
										.dctType(TEST_CO_TYPE)
										.id(TEST_CO_ID)
										.ecmIdentifier(TEST_CO_ECM_ID)
										.aggregationType(TEST_CO_AGG_TYPE)
										.date(new DateTime(TEST_CO_DATE))
										.build();
	}
	
	public static ContentObjectMetadata completedMultipartContentObject() {

		return new ContentObjectMetadata.Builder()
										.title(TEST_CO_TITLE)
										.dctType(TEST_CO_TYPE)
										.id(TEST_CO_ID)
										.ecmIdentifier(TEST_CO_ECM_ID)
										.aggregationType(TEST_CO_AGG_TYPE)
										.date(new DateTime(TEST_CO_DATE))
										.withAssetMetadata()
											.id(TEST_ASSET_1_ID)
											.format(TEST_ASSET_1_FORMAT)
											.assetType(TEST_ASSET_1_TYPE)
											.version(TEST_ASSET_1_VERSION)
											.byteCount(TEST_ASSET_1_BYTECOUNT)
											.filename(TEST_ASSET_1_FILENAME)
											.isFormatOf(TEST_ASSET_1_IS_FORMAT_OF)
											.mode(TEST_ASSET_1_MODE)
											.isMultipart(TEST_ASSET_1_IS_MULTIPART)
											.createdOn(new DateTime(TEST_ASSET_1_CREATED))
											.sequence(TEST_ASSET_1_SEQUENCE_NO)
											.insert()
										.withAssetMetadata()
											.id(TEST_ASSET_2_ID)
											.format(TEST_ASSET_2_FORMAT)
											.assetType(TEST_ASSET_2_TYPE)
											.version(TEST_ASSET_2_VERSION)
											.byteCount(TEST_ASSET_2_BYTECOUNT)
											.filename(TEST_ASSET_2_FILENAME)
											.isFormatOf(TEST_ASSET_2_IS_FORMAT_OF)
											.mode(TEST_ASSET_2_MODE)
											.isMultipart(TEST_ASSET_2_IS_MULTIPART)
											.createdOn(new DateTime(TEST_ASSET_2_CREATED))
											.sequence(TEST_ASSET_2_SEQUENCE_NO)
											.insert()
										.withAssetMetadata()
											.id(TEST_ASSET_3_ID)
											.format(TEST_ASSET_3_FORMAT)
											.assetType(TEST_ASSET_3_TYPE)
											.version(TEST_ASSET_3_VERSION)
											.byteCount(TEST_ASSET_3_BYTECOUNT)
											.filename(TEST_ASSET_3_FILENAME)
											.isFormatOf(TEST_ASSET_3_IS_FORMAT_OF)
											.mode(TEST_ASSET_3_MODE)
											.isMultipart(TEST_ASSET_3_IS_MULTIPART)
											.createdOn(new DateTime(TEST_ASSET_3_CREATED))
											.sequence(TEST_ASSET_3_SEQUENCE_NO)
											.insert()
										.withGenerationMetadata()
											.isGenerationOf(TEST_GEN_IS_GENERATION_OF)
											.classificationLevel(TEST_GEN_CLASSIFICATION_LEVEL)
											.stage(TEST_GEN_STAGE)
											.withAsset(TEST_GEN_ASSET_1)
											.withAsset(TEST_GEN_ASSET_2)
											.withAsset(TEST_GEN_ASSET_3)
											.insert()
										.build();
	}

}
