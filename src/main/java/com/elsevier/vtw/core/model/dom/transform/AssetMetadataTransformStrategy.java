package com.elsevier.vtw.core.model.dom.transform;

import com.elsevier.vtw.core.model.dom.AssetMetadata;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface AssetMetadataTransformStrategy {

	ObjectNode transformToJson(AssetMetadata assetMetadata);

	AssetMetadata transformFromJson(ObjectNode node);

}
