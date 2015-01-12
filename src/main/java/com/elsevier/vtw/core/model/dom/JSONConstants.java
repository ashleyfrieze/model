package com.elsevier.vtw.core.model.dom;

public final class JSONConstants {

	public static final String DCT_TITLE = "dct:title";
	public static final String DCT_CREATOR = "dct:creator";
	public static final String DCT_DATE = "dct:date";
	public static final String BAM_YEAR = "bam:year";
	public static final String DCT_MODIFIED = "dct:modified";
	public static final String BAM_LST_REVIEW_DT = "bam:lastReviewdate";
	public static final String SAT_VERSION = "sat:version";
	public static final String PRISM_COPYRIGHT = "prism:copyright";
	public static final String DCT_PUBLISHER = "dct:publisher";
	public static final String DCT_ABSTRACT = "dct:abstract";
	public static final String DCT_DESCRIPTION = "dct:description";
	public static final String DCT_LANGUAGE = "dct:language";
	public static final String DCT_SUBJECT = "dct:subject";
	public static final String PRISM_KEYWORD = "prism:keyword";
	public static final String PRISM_AGGR_TYPE = "prism:aggregationType";
	public static final String DCT_TYPE = "dct:type";
	public static final String PRISM_CHANNEL = "prism:channel";

	public static final String BAM_SUBTYPE = "bam:subtype";
	public static final String PRISM_PUBLICATION_NAME = "prism:publicationName";
	public static final String PRISM_ISSN = "prism:issn";
	public static final String PRISM_ISBN = "prism:isbn";
	public static final String PRISM_COVERDATE = "prism:coverdate";
	public static final String PRISM_COVERDISPDATE = "prism:coverDisplayDate";
	public static final String PRISM_VOLUME = "prism:volume";
	public static final String PRISM_NUMBER = "prism:number";
	public static final String PRISM_PAGE_RANGE = "prism:pageRange";
	public static final String PRISM_STARTING_PAGE = "prism:startingPage";
	public static final String PRISM_ENDING_PAGE = "prism:endingPage";
	public static final String ECM_IDENTIFIER = "ecm:identifier";
	public static final String DCT_IS_PART_OF = "dct:isPartOf";
	public static final String BAM_ASSET_METADATA = "bam:Assetmetadata";
	public static final String METADATA_TYPE = "bam:Metadata";
	public static final String BAM_HAS_ASSET_METADATA = "bam:hasAssetMetadata";
	public static final String BAM_HAS_ASSET = "bam:hasAsset";
	public static final String DCT_FORMAT = "dct:format";
	public static final String DCT_IS_FORMAT_OF = "dct:isFormatOf";
	public static final String PRISM_BYTE_COUNT = "prism:byteCount";
	public static final String BAM_ASSET_TYPE = "bam:assetType";
	public static final String BAM_ASSET_VERSION = "bam:assetVersion";
	public static final String BAM_MODE = "bam:mode";
	public static final String BAM_CREATED = "bam:created";
	public static final String RDF_ABOUT = "rdf:about";
	public static final String RDF_RESOURCE = "rdf:resource";
	public static final String RDF_SEQUENCE = "rdf:seq";
	public static final String NAMESPACE_PREFIX = "http://data.elsevier.com/vocabulary/ElsevierContentTypes/";
	public static final String DCT_IDENTIFIER = "dct:identifier";
	public static final String BAM_PROJECT = "bam:project";
	public static final String ECM_IS_FUNDED_BY = "ecm:isFundedBy";

	// added for annotation satellite recipe changed
	public static final String ECM_HAS_CONCEPT = "ecm:hasConceptScheme";

	// added for Metadata and AssetMetadata Audit fields
	public static final String VTW_LASTMODIFIEDBY = "vtw:lastModifiedBy";
	public static final String VTW_SEQ = "vtw:sequence";
	public static final String VTW_LASTUPDATED_TIME = "vtw:lastUpdatedTimestamp";
	public static final String VTW_ACTION = "vtw:action";
	public static final String VTW_CREATED_TIME = "vtw:crtdTm";
	public static final String VTW_AM_TIMESTAMP = "vtw:assetMetadataTimestamp";

	// added for JsonLD
	public static final String JSONLD_ID = "@id";
	public static final String JSONLD_CONTEXT = "@context";
	public static final String JSONLD_TYPE = "@type";
	public static final String JSONLD_ID_PREFIX = "http://vtw.elsevier.com/content/";
	public static final String JSONLD_CONTEXT_URL_SUFFIX = "/metadata/context.jsonld";

	// added for upload request having multiple content objects
	public static final String BAM_HAS_CONTENT_OBJECTS = "bam:hasContentObjects";
	public static final String DCT_CREATED = "dct:created";

	public static final String ERROR = "error";

	// added for Generations
	public static final String BAM_HAS_GENERATION = "bam:hasGeneration";
	public static final String BAM_GENERATION = "bam:generation";
	public static final String BAM_GENERATION_VALUE = "bam:Generation";

	public static final String BAM_CLASSIFICATION_LEVEL = "bam:classificationLevel";
	public static final String BAM_STAGE = "bam:stage";
	public static final String BAM_CLASSIFICATION = "bam:classificationLevel";
	public static final String BAM_IS_GENERATION_OF = "bam:isGenerationOf";
	public static final String DCT_HASPART = "dct:hasPart";
	public static final String DCT_REQUIRES = "dct:requires";

	// added for asset and generation events
	public static final String LASTUPDATED = "lastupdated";

	public static final String PRISM_EXPIRATION_DATE = "prism:expirationDate";

	// added for CAR
	public static final String BAM_ISCAROF = "bam:isCitationAbstractReferenceOf";

	public static final String ID = "ID";

	public static final String CLASS_AUTHOR = "author";
	public static final String CLASS_EDITOR = "editor";
	public static final String CLASS_TEASER = "teaser";
	public static final String CREATOR_NAME_FIELD = "foaf:name";
	public static final String ORCID_URL = "http://orcid.org/";
	public static final String ORCID_ATTR = "@orcid";

	public static final String BAM_PUBLICATION_TIME_START = "bam:publicationTimeStart";
	public static final String BAM_PUBLICATION_TIME_END = "bam:publicationTimeEnd";

	public static final String RDFS_LABEL = "rdfs:label";
	public static final String DCT_SOURCE = "dct:source";

	public static final String DCT_HAS_PART = "dct:hasPart";

	public static final String PRISM_VERSION_IDENTIFIER = "prism:versionIdentifier";

	public static final String BAM_FUNDINGBODY_TYPE = "bam:fundingBodyType";
	public static final String BAM_FUNDINGBODY_COUNTRY = "bam:fundingBodyCountry";

	public static final String BAM_ACTION = "bam:action";

	public static final String ASSETMETADATA_TYPE = "bam:AssetMetadata";

	public static final String BAM_PIXELS_HIGH = "bam:pixelsHigh";
	public static final String BAM_PIXELS_WIDE = "bam:pixelsWide";
	public static final String BAM_UCS_LOCATION = "bam:ucsLocation";
	public static final String BAM_FILE_NAME = "bam:filename";
	public static final String BAM_MULTI_PART = "bam:multiPart";
	public static final String BAM_MULTI_PART_SEQUENCE_NUMBER = "bam:sequenceNumber";
	public static final String BAM_MULTI_PART_TYPE_ASSET_LIST = "bam:AssetList";
	public static final String BAM_MULTI_PART_HAS_ASSET_LIST = "bam:hasAssetList";
	public static final String BAM_MULTI_PART_HAS_FIRST_ITEM = "bam:hasFirstItem";
	public static final String BAM_MULTI_PART_NEXT_ITEM = "bam:nextItem";
	public static final String BAM_MULTI_PART_HAS_ASSET_SEQUENCE = "bam:hasAssetSequence";
	public static final String BAM_MULTI_PART_TYPE_ASSET_SEQUENCE = "bam:AssetListItem";
	public static final String BAM_MULTI_PART_ASSET_ITEM_ASSET = "bam:itemAsset";

	// Added prism:doi,bam:formattedPII for recipe v25
	public static final String PRISM_DOI = "prism:doi";
	public static final String BAM_FORMATTED_PII = "bam:formattedPII";
	// Metadata2.5 changes
	public static final String BAM_DESCRIBES_RESOURCE = "bam:describesResource";

	public static final String BAM_CONTRIBUTOR_VERSION_IDENTIFIER = "bam:contributorVersionIdentifier";

	public static final String BAM_CONTRIBUTOR = "bam:contributor";

	public static final String DCT_CONFORMS_TO = "dct:conformsTo";

	public static final String BAM_IS_BASED_ON = "bam:isBasedOn";

	// Added bam:asjcCode for Third Party Book
	public static final String BAM_ASJC_CODE = "bam:asjcCode";

	public static final String BAM_TITLE = "bam:title";
	public static final String BAM_SUBTITLE = "bam:subtitle";
	public static final String PRISM_EDITION = "prism:edition";
	public static final String BAM_COPYRIGHT_YEAR = "bam:copyrightYear";
	public static final String BAM_IMPRINT = "bam:imprint";
	public static final String BAM_OPERATING_COMPANY = "bam:operatingCompany";
	public static final String BAM_LEAD_CREATOR = "bam:leadCreator";
	public static final String BAM_COLLECTION_CODE = "bam:collectionCode";
	public static final String BAM_REJECTION_REASON = "bam:rejectionReason";

}
