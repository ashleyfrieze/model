package com.elsevier.json.wrapper.teststructures;

import com.elsevier.json.wrapper.Field;
import com.elsevier.json.wrapper.Wrapper;

/**
 * Represents something where there are two actual properties and then
 * a virtual property that's made of the actual ones via Java 8 default
 * methods.
 */
public interface DefaultProperties extends Wrapper {
	@Field("first")
	String getFirstName();
	void setFirstName(String firstName);
	
	@Field("last")
	String getLastName();
	void setLastName(String lastName);

	default public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
	default public void setFullName(String fullName) {
		String[] split = fullName.split(" ");
		setFirstName(split[0]);
		setLastName(split[1]);
	}
}
