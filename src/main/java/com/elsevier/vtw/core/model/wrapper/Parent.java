package com.elsevier.vtw.core.model.wrapper;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

import com.elsevier.vtw.core.model.wrapper.internal.Field;

public interface Parent extends ContentObjectMetadata {
	@Field(BAM_ASSET_METADATA)
	AssetMetadata getAssetMetadata();
}
