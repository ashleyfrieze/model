package com.elsevier.json.wrapper.teststructures;

import com.elsevier.json.wrapper.Field;
import com.elsevier.json.wrapper.Wrapper;

/**
 * 
 * Example, where there's a sub object we want to have from deep in the hierarchy
 * that we want to pretend is a child
 *
 */
public interface FlattenADeepSubObject extends Wrapper {
	@Field(path={"a","b","c"})
	FirstAndLastName getFirstAndLast();
	void setFirstAndLast(FirstAndLastName obj);
}
