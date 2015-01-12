package com.elsevier.vtw.core.model.dom;

import com.elsevier.vtw.core.model.dom.JSONConstants;
import com.elsevier.vtw.core.model.dom.GenerationMetadata;
import com.elsevier.vtw.core.model.dom.transform.ContentObjectMetadataTransformStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ContentObjectMetadata {	

	private String type;
	private String id;
	private String ecmIdentifier;
	private String title;
	private DateTime date;
	private String dctType;
	private List<String> aggregationTypes;
	private List<String> prismChannel;
	private List<AssetMetadata> assetMetadata;
	private List<GenerationMetadata> generationMetadata;

	private ContentObjectMetadata(String type, String id, String ecmIdentifier,
			String title, List<String> aggregationTypes, String dctType,
			DateTime date, List<AssetMetadata> assetMetadata,
			List<GenerationMetadata> generationMetadata) {

		this.type = type;
		this.id = id; 
		this.ecmIdentifier = ecmIdentifier;
		this.title = title;
		this.aggregationTypes = aggregationTypes;
		this.dctType = dctType;
		this.date = date;
		this.assetMetadata = assetMetadata;
		this.generationMetadata = generationMetadata;
	}

	public String getType() {return type;}

	public String getId() {
		return id;
	}

	public String getEcmIdentifier() {
		return ecmIdentifier;
	}

	public String getTitle() {
		return title;
	}

	public ReadableDateTime getDate() {
		return date;
	}

	public String getDctType() {
		return dctType;
	}

	public List<String> getAggregationType() {
		return Collections.unmodifiableList(aggregationTypes);
	}

	public List<String> getPrismChannel() {
		Collections.unmodifiableList(prismChannel);
		return null;
	}

	public List<AssetMetadata> getAssetMetadata() {
		return Collections.unmodifiableList(assetMetadata);
	}

	public List<GenerationMetadata> getGenerationMetadata() {
		return Collections.unmodifiableList(generationMetadata);
	}

	public ObjectNode toJson(ContentObjectMetadataTransformStrategy transformer) {
		return transformer.transformToJson(this);
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {

		private String type;
		private String id;
		private String ecmIdentifier;
		private String title;
		private List<String> aggregationType = new ArrayList<>();
		private String dctType;
		private DateTime date;
		private List<AssetMetadata> assetMetadata = new ArrayList<>();
		private List<GenerationMetadata> generationMetadata = new ArrayList<>();
	
		public Builder() {
			this.type = JSONConstants.METADATA_TYPE;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		public Builder ecmIdentifier(String ecmIdentifier) {
			this.ecmIdentifier = ecmIdentifier;
			return this;
		}
		
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		
		public Builder aggregationType(String aggregationType) {
			this.aggregationType.add(aggregationType);
			return this;
		}
		
		public Builder dctType(String type) {
			this.dctType = type;
			return this;
		}
		
		public Builder date(DateTime date) {
			this.date = date;
			return this;
		}
		
		public AssetMetadata.Builder withAssetMetadata() {

			return new AssetMetadata.Builder(this);
		}
		
		public Builder withAssetMetadata(AssetMetadata am) {
			this.assetMetadata.add(am);
			return this;
		}

		public GenerationMetadata.Builder withGenerationMetadata() {

			return new GenerationMetadata.Builder(this);
		}
		
		public Builder withGenerationMetadata(GenerationMetadata gm) {
			this.generationMetadata.add(gm);
			return this;
		}

		public ContentObjectMetadata fromJson(ContentObjectMetadataTransformStrategy transform, String value) {
			return transform.transformFromJson(value);
		}

		public ContentObjectMetadata fromJson(ContentObjectMetadataTransformStrategy transform, ObjectNode node) {
			return transform.transformFromJson(node);
		}

		public ContentObjectMetadata build() {

			return new ContentObjectMetadata(this.type, this.id,
					this.ecmIdentifier,
					this.title,
					this.aggregationType,
					this.dctType,
					this.date,
					this.assetMetadata,
					this.generationMetadata);
		}
		
	}

}
