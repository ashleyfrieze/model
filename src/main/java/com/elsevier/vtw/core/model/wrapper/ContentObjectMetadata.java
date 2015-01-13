package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Property;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

public interface ContentObjectMetadata extends Wrapper {
	@Field(JSONLD_ID)
	Property<String> id();
	
	@Field(ECM_IDENTIFIER)
	Property<String> ecmIdentifier();

	@Field(DCT_TITLE)
	Property<String> title();
}
