package com.elsevier.vtw.core.model.dom.transform;

import com.elsevier.vtw.core.model.dom.AssetMetadata;
import com.elsevier.vtw.core.model.dom.AssetMetadata.Builder;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

public class DefaultAssetMetadataTransformImpl extends AbstractTransformImpl implements
		AssetMetadataTransformStrategy {


	@Override
	public ObjectNode transformToJson(AssetMetadata assetMetadata) {
		ObjectNode assetNode = FACTORY.objectNode();
		
		assetNode.put(JSONLD_TYPE, assetMetadata.getType());
		assetNode.put(JSONLD_ID, assetMetadata.getId());
		assetNode.put(DCT_FORMAT, assetMetadata.getFormat());
		assetNode.put(BAM_ASSET_TYPE, assetMetadata.getAssetType());
		assetNode.put(BAM_ASSET_VERSION, assetMetadata.getVersion());
		assetNode.put(PRISM_BYTE_COUNT, assetMetadata.getByteCount());
		assetNode.put(BAM_FILE_NAME, assetMetadata.getFilename());
		assetNode.put(DCT_IS_FORMAT_OF, assetMetadata.getIsFormatOf());
		assetNode.put(BAM_MODE, assetMetadata.getMode());
		assetNode.put(BAM_MULTI_PART, assetMetadata.isMultipart());
		assetNode.put(BAM_CREATED, dateFormatter.print(assetMetadata.getCreatedOn()));
		assetNode.put(BAM_MULTI_PART_SEQUENCE_NUMBER, assetMetadata.getSequence());
		
		return assetNode;
	}

	@Override
	public AssetMetadata transformFromJson(ObjectNode node) {


		return new Builder()
				.type(safeString(node, JSONLD_TYPE))
				.id(safeString(node, JSONLD_ID))
				.format(safeString(node, DCT_FORMAT))
				.assetType(safeString(node, BAM_ASSET_TYPE))
				.version(safeString(node, BAM_ASSET_VERSION))
				.byteCount(safeInt(node, PRISM_BYTE_COUNT))
				.filename(safeString(node, BAM_FILE_NAME))
				.isFormatOf(safeString(node, DCT_IS_FORMAT_OF))
				.mode(safeString(node, BAM_MODE))
				.isMultipart(Boolean.parseBoolean(safeString(node, BAM_MULTI_PART)))
				.createdOn(new DateTime(safeString(node, BAM_CREATED)))
				.sequence(safeInt(node, BAM_MULTI_PART_SEQUENCE_NUMBER))
				.build();
	}

}
