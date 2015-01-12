package com.elsevier.vtw.core.model.dom.transform;

import com.elsevier.vtw.core.model.GenerationMetadata;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface GenerationMetadataTransformStrategy {

	ObjectNode transformToJson(GenerationMetadata generationMetadata);

	GenerationMetadata transformFromJson(ObjectNode node);
}
