package com.elsevier.vtw.core.model.dom.transform;

import com.elsevier.vtw.core.model.GenerationMetadata;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static com.elsevier.vtw.common.JSONConstants.*;

public class DefaultGenMetadataTransformImpl extends AbstractTransformImpl implements
		GenerationMetadataTransformStrategy {

	@Override
	public ObjectNode transformToJson(GenerationMetadata generationMetadata) {
		ObjectNode genNode = FACTORY.objectNode();
		
		genNode.put(JSONLD_TYPE, BAM_GENERATION_VALUE);
		genNode.put(BAM_CLASSIFICATION_LEVEL, generationMetadata.getClassificationLevel());
		genNode.put(BAM_STAGE, generationMetadata.getStage());
		genNode.put(BAM_IS_GENERATION_OF, generationMetadata.getIsGenerationOf());
		
		if (!generationMetadata.getAssets().isEmpty()) {
			ArrayNode assets = genNode.putArray(BAM_HAS_ASSET);
			for(String asset : generationMetadata.getAssets()) {
				assets.add(asset);
			}
		}
		
		return genNode;
	}

	@Override
	public GenerationMetadata transformFromJson(ObjectNode node) {

		GenerationMetadata.Builder builder = GenerationMetadata.builder();

		builder.isGenerationOf(safeString(node, BAM_IS_GENERATION_OF));
		builder.stage(safeString(node, BAM_STAGE));
		builder.classificationLevel(safeString(node, BAM_CLASSIFICATION_LEVEL));

		return builder.build();

	}

}
