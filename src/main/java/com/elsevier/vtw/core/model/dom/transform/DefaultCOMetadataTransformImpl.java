package com.elsevier.vtw.core.model.dom.transform;

import com.elsevier.vtw.core.model.dom.AssetMetadata;
import com.elsevier.vtw.core.model.dom.ContentObjectMetadata;
import com.elsevier.vtw.core.model.dom.ContentObjectMetadata.Builder;
import com.elsevier.vtw.core.model.dom.GenerationMetadata;
import com.elsevier.vtw.core.model.dom.VTWException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;

import java.io.IOException;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

/**
 * Default implementation of the ContentObjectMetadata Transformer.
 *
 */
public class DefaultCOMetadataTransformImpl 
				extends AbstractTransformImpl 
				implements ContentObjectMetadataTransformStrategy{

	@Override
	public ObjectNode transformToJson(
			ContentObjectMetadata contentObjectMetadata) {		

		ObjectNode coNode = FACTORY.objectNode();
		
		coNode.put(JSONLD_CONTEXT, "http://vtw.elsevier.com/metadata/context.jsonld");
		coNode.put(JSONLD_TYPE, contentObjectMetadata.getType());
		
		coNode.put(DCT_TITLE, contentObjectMetadata.getTitle());
		coNode.put(DCT_TYPE, contentObjectMetadata.getDctType());
		coNode.put(DCT_DATE, dateFormatter.print(contentObjectMetadata.getDate()));
		
		coNode.put(JSONLD_ID, contentObjectMetadata.getId());
		coNode.put(ECM_IDENTIFIER, contentObjectMetadata.getEcmIdentifier());
		
		transformToAggregationType(contentObjectMetadata, coNode);
		transformToAssetMetadata(contentObjectMetadata, coNode);		
		transformToGenerationMetadata(contentObjectMetadata, coNode);
		
		return coNode;
	}

	private void transformToGenerationMetadata(
			ContentObjectMetadata contentObjectMetadata, ObjectNode coNode) {
		if (!contentObjectMetadata.getGenerationMetadata().isEmpty()) {
			
			GenerationMetadataTransformStrategy genTransformer = new DefaultGenMetadataTransformImpl();
			ArrayNode genArray = coNode.putArray(BAM_HAS_GENERATION);
			
			for (GenerationMetadata gm : contentObjectMetadata.getGenerationMetadata()) {
				ObjectNode genNode = genTransformer.transformToJson(gm);
				genArray.add(genNode);
			}
		}
	}

	private void transformToAssetMetadata(
			ContentObjectMetadata contentObjectMetadata, ObjectNode coNode) {
		if (!contentObjectMetadata.getAssetMetadata().isEmpty()) {
			
			AssetMetadataTransformStrategy amTransformer = new DefaultAssetMetadataTransformImpl();
			ArrayNode asArray = coNode.putArray(BAM_HAS_ASSET_METADATA);
			
			for(AssetMetadata am : contentObjectMetadata.getAssetMetadata()) {
				ObjectNode amNode = amTransformer.transformToJson(am);
				asArray.add(amNode);
			}
		}
	}

	private void transformToAggregationType(
			ContentObjectMetadata contentObjectMetadata, ObjectNode coNode) {
		if (!contentObjectMetadata.getAggregationType().isEmpty()) {
			ArrayNode agNode = coNode.putArray(PRISM_AGGR_TYPE);
			for (String agType : contentObjectMetadata.getAggregationType()) {
				agNode.add(agType);
			}
		}
	}

	public ContentObjectMetadata transformFromJson(String value) {
		try {
			JsonNode node = MAPPER.readTree(value);
			return transformFromJson((ObjectNode) node);
		} catch (IOException e) {
			throw new VTWException("Could not read the string {value} as valid JSON", e);
		}
	}

	@Override
	public ContentObjectMetadata transformFromJson(ObjectNode node) {

		ContentObjectMetadata.Builder builder = new ContentObjectMetadata.Builder();
		
		builder.id(safeString(node, JSONLD_ID))
			.ecmIdentifier(safeString(node, ECM_IDENTIFIER))
			.title(safeString(node, DCT_TITLE))
			.dctType(safeString(node, DCT_TYPE))
			.date(new DateTime(safeString(node, DCT_DATE)));

		transformFromAggregationType(builder, node.findValue(PRISM_AGGR_TYPE));
		
		transformFromAssetMetaData(builder, node.findValue(BAM_HAS_ASSET_METADATA));
		
		transformFromGenerationMetadata(builder, node.findValue(BAM_HAS_GENERATION));

		return builder.build();
	}

	private void transformFromGenerationMetadata(Builder builder,
			JsonNode value) {
		
		if(value != null && value.isArray()) {
			GenerationMetadataTransformStrategy genTransformer = new DefaultGenMetadataTransformImpl();
			for (JsonNode jsonNode : value) {
				
				if (jsonNode.isObject()) {
					GenerationMetadata gm = genTransformer.transformFromJson((ObjectNode) jsonNode);
					builder.withGenerationMetadata(gm);
				}
			}
		}

		
	}

	private void transformFromAssetMetaData(Builder builder, JsonNode value) {
		if(value != null && value.isArray()) {
			AssetMetadataTransformStrategy amTransformer = new DefaultAssetMetadataTransformImpl();
			for (JsonNode jsonNode : value) {
				if (jsonNode.isObject()) {
					AssetMetadata am = amTransformer.transformFromJson((ObjectNode) jsonNode);
					builder.withAssetMetadata(am);
				}
			}
		}
	}

	private void transformFromAggregationType(Builder builder,
			JsonNode value) {
		if(value != null && value.isArray()) {
			for (JsonNode jsonNode : value) {
				builder.aggregationType(jsonNode.asText());
			}
		}		
	}


}
