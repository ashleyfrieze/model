package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Property;

public interface AssetMetadata extends ContentObjectMetadata {
	@Field("dct:format")
	Property<String> dctFormat();
}
