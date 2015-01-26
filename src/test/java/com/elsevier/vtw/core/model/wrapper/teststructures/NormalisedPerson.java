package com.elsevier.vtw.core.model.wrapper.teststructures;

import com.elsevier.vtw.core.model.wrapper.Field;
import com.elsevier.vtw.core.model.wrapper.Normalised;
import com.elsevier.vtw.core.model.wrapper.Wrapper;

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
