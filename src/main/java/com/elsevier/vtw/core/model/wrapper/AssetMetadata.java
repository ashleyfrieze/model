package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import static com.elsevier.vtw.core.model.dom.JSONConstants.*;

public interface AssetMetadata extends ContentObjectMetadata {
	@Field(DCT_FORMAT)
	String getDctFormat();
	void setDctFormat(String format);
}
