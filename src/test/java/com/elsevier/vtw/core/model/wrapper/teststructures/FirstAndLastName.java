package com.elsevier.vtw.core.model.wrapper.teststructures;

import com.elsevier.vtw.core.model.wrapper.Field;
import com.elsevier.vtw.core.model.wrapper.Wrapper;

public interface FirstAndLastName extends Wrapper {
	@Field("first")
	String getFirstName();
	void setFirstName(String firstName);
	
	@Field("last")
	String getLastName();
	void setLastName(String lastName);
}
