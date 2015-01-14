package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.Field;
import com.elsevier.vtw.core.model.wrapper.internal.Wrapper;

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
