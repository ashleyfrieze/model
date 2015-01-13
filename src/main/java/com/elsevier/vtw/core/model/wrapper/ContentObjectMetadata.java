package com.elsevier.vtw.core.model.wrapper;

import org.joda.time.DateTime;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

public interface ContentObjectMetadata extends Wrapper {
	@Field(JSONLD_ID)
	String getId();
	void setId(String id);
	
	String getEcmIdentifier();
	@Field(ECM_IDENTIFIER)
	void setEcmIdentifier(String identifier);
	
	@Field(DCT_TITLE)
	String getTitle();
	void setTitle(String title);
	
	@Field(DCT_DATE)
	String getDateAsString();
	void setDateAsString(String date);
	
	@Field(DCT_DATE)
	DateTime getDate();
	void setDate(DateTime date);
}

