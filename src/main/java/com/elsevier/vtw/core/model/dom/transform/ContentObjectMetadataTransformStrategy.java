package com.elsevier.vtw.core.model.dom.transform;

import com.elsevier.vtw.core.model.dom.ContentObjectMetadata;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ContentObjectMetadataTransformStrategy {

	ObjectNode transformToJson(ContentObjectMetadata contentObjectMetadata);

	ContentObjectMetadata transformFromJson(ObjectNode node);

	ContentObjectMetadata transformFromJson(String value);

}
