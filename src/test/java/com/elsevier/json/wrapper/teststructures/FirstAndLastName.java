package com.elsevier.json.wrapper.teststructures;

import com.elsevier.json.wrapper.Field;
import com.elsevier.json.wrapper.Wrapper;

public interface FirstAndLastName extends Wrapper {
	@Field("first")
	String getFirstName();
	void setFirstName(String firstName);
	
	@Field("last")
	String getLastName();
	void setLastName(String lastName);
}
