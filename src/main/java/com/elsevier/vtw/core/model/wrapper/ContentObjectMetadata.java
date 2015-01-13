package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

public interface ContentObjectMetadata extends Wrapper {
	@Field(JSONLD_ID)
	String getId();
	@Field(JSONLD_ID)
	void setId(String id);
	
	@Field(ECM_IDENTIFIER)
	String getEcmIdentifier();
	@Field(ECM_IDENTIFIER)
	void setEcmIdentifier(String identifier);
	
	@Field(DCT_TITLE)
	String getTitle();
	@Field(DCT_TITLE)
	void setTitle(String title);
}
