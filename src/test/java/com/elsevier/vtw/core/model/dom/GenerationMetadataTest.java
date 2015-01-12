package com.elsevier.vtw.core.model.dom;

import com.elsevier.vtw.core.model.GenerationMetadata;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class GenerationMetadataTest {
	
	@Test
	public void checkSimpleDataForGenerationMetadata() {
		
		GenerationMetadata generation = GenerationMetadata.builder()
											.classificationLevel("cl")
											.stage("st")
											.isGenerationOf("igo")
											.withAsset("1")
											.withAsset("2")
											.withAsset("3")
											.build();
		
		assertThat("", generation.getClassificationLevel(), equalTo("cl"));
		assertThat("", generation.getStage(), equalTo("st"));
		assertThat("", generation.getIsGenerationOf(), equalTo("igo"));
		assertThat("", generation.getAssets().size(), equalTo(3));
		
	}

}
