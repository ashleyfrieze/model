package com.elsevier.vtw.core.model.dom;

import com.elsevier.vtw.core.model.dom.transform.GenerationMetadataTransformStrategy;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GenerationMetadata {

	private String classificationLevel;
	private String stage;
	private String isGenerationOf;
	private Set<String> assets;

	private GenerationMetadata(String classificationLevel, String stage,
							   String isGenerationOf, Set<String> assets) {
		this.classificationLevel = classificationLevel;
		this.stage = stage;
		this.isGenerationOf = isGenerationOf;
		this.assets = assets;
	}

	public String getClassificationLevel() {
		return classificationLevel;
	}

	public String getStage() {
		return stage;
	}

	public String getIsGenerationOf() {
		return isGenerationOf;
	}

	public Set<String> getAssets() {
		return Collections.unmodifiableSet(assets);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private ContentObjectMetadata.Builder parent;
		private String classificationLevel;
		private String stage;
		private String isGenerationOf;
		private Set<String> assets = new HashSet<>();

		public Builder(ContentObjectMetadata.Builder parent) {
			this.parent = parent;
		}

		public Builder(GenerationMetadataTransformStrategy transformer, ObjectNode json) {
		}

		public Builder() {
		}

		public GenerationMetadata build() {
			GenerationMetadata generation = new GenerationMetadata(classificationLevel,
					stage, isGenerationOf, assets);
			return generation;
		}

		public Builder classificationLevel(String classificationLevel) {
			this.classificationLevel = classificationLevel;
			return this;
		}

		public Builder stage(String stage) {
			this.stage = stage;
			return this;
		}

		public Builder isGenerationOf(String isGenerationOf) {
			this.isGenerationOf = isGenerationOf;
			return this;
		}

		public Builder withAsset(String asset) {
			assets.add(asset);
			return this;
		}

		public ContentObjectMetadata.Builder insert() {
			GenerationMetadata gm = build();
			return parent.withGenerationMetadata(gm);

		}

	}

}
