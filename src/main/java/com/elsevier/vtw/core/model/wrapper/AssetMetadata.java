package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

public interface AssetMetadata extends ContentObjectMetadata {
	@Field(DCT_FORMAT)
	String getDctFormat();
	void setDctFormat(String format);
	
	// Example where field name is inferred
	@Field
	String getInferredField();
	void setInferredField(String field);
	
	// Example where field name is inferred and is only 1 character
	@Field
	String getA();
	void setA(String a);
}
