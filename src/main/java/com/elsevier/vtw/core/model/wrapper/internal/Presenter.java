package com.elsevier.vtw.core.model.wrapper.internal;

public interface Presenter<WRAPPERTYPE, PROPERTYTYPE> {
	PROPERTYTYPE get(WRAPPERTYPE source);
	void set(WRAPPERTYPE destination, PROPERTYTYPE value);
}
