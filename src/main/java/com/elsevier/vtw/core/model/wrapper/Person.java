package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Normalised;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

public interface Person extends Wrapper {
	@Field("gender")
	String getGender();
	void setGender(String gender);
	
	@Normalised
	FirstAndLastName getFirstAndLastName();
}
