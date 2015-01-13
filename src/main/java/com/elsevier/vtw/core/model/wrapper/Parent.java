package com.elsevier.vtw.core.model.wrapper;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

import com.elsevier.vtw.core.model.wrapper.internal.ArrayProperty;
import com.elsevier.vtw.core.model.wrapper.internal.Field;

public interface Parent extends ContentObjectMetadata {
	@Field("bam:hasAssetMetadata")
	ArrayProperty<AssetMetadata> assetMetadata();
}
