package com.elsevier.vtw.core.model.wrapper;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

import com.elsevier.vtw.core.model.wrapper.internal.ArrayWrapper;
import com.elsevier.vtw.core.model.wrapper.internal.Field;

public interface Parent extends ContentObjectMetadata {
	// simple child object
	@Field(BAM_ASSET_METADATA)
	AssetMetadata getAssetMetadata();
	void setAssetMetadata(AssetMetadata assetMetadata);
	
	// example with array of strings
	@Field("stringarray")
	ArrayWrapper<String> getStringArray();
	void setStringArray(ArrayWrapper<String> array);
	
	// example with array of sub objects
	@Field("assetMetaDataArray")
	ArrayWrapper<AssetMetadata> getAssetArray();
	void setAssetArray(ArrayWrapper<AssetMetadata> array);
}
