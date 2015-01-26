package com.elsevier.json.wrapper.teststructures;

import com.elsevier.json.wrapper.Field;
import com.elsevier.json.wrapper.Presented;
import com.elsevier.json.wrapper.Wrapper;

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
