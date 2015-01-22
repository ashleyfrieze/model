package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Presented;
import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

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
