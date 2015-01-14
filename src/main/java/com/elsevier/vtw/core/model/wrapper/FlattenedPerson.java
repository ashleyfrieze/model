package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Normalised;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

/**
 * Example of how to treat properties of a sub object as though local
 * e.g.
 * 
 * make
 *     person.gender
 *     person.firstandlast.first
 *     person.firstandlast.last
 *     
 * look like
 * 
 *     person.gender
 *     person.first
 *     person.last
 *
 */
public interface FlattenedPerson extends Wrapper {
	@Field("gender")
	String getGender();
	void setGender(String gender);
	
	// these fields flatten the first and last object
	@Field(path={"firstandlast","first"})
	String getFirstName();
	void setFirstName(String first);
	
	@Field(path={"firstandlast","last"})
	String getLastName();
	void setLastName(String last);
	
	// yet the first and last object can also co-exist as a sub object
	// in the interface - a handy side effect of wrapping
	@Field("firstandlast")
	FirstAndLastName getFirstAndLast();
	void setFirstAndLast(FirstAndLastName firstAndLast);
	
}
