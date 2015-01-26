package com.elsevier.vtw.core.model.wrapper.teststructures;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

public interface FirstAndLastName extends Wrapper {
	@Field("first")
	String getFirstName();
	void setFirstName(String firstName);
	
	@Field("last")
	String getLastName();
	void setLastName(String lastName);
}
