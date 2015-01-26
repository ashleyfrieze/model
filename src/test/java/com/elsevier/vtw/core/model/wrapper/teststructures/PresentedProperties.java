package com.elsevier.vtw.core.model.wrapper.teststructures;

import com.elsevier.vtw.core.model.wrapper.Field;
import com.elsevier.vtw.core.model.wrapper.Presented;
import com.elsevier.vtw.core.model.wrapper.Wrapper;

/**
 * Represents something where there are two actual properties and then
 * a virtual property that's somehow made of the actual ones
 */
public interface PresentedProperties extends Wrapper {
	@Field("first")
	String getFirstName();
	void setFirstName(String firstName);
	
	@Field("last")
	String getLastName();
	void setLastName(String lastName);
	
	@Presented(presenter=FullName.class)
	String getFullName();
	void setFullName(String fullName);
}
