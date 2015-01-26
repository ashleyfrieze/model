package com.elsevier.json.wrapper.teststructures;

import com.elsevier.json.wrapper.Field;
import com.elsevier.json.wrapper.Normalised;
import com.elsevier.json.wrapper.Wrapper;

/**
 * Example of how to treat a tuple of properties as though a sub object
 * e.g.
 * make
 *     person.gender
 *     person.first
 *     person.last
 *     
 * look like
 * 
 *     person.gender
 *     person.firstandlast.first
 *     person.firstandlast.last
 *
 */
public interface NormalisedPerson extends Wrapper {
	@Field("gender")
	String getGender();
	void setGender(String gender);
	
	@Normalised
	FirstAndLastName getFirstAndLastName();
}
