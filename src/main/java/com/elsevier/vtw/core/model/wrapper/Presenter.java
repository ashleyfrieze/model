package com.elsevier.vtw.core.model.wrapper;

public interface Presenter<WRAPPERTYPE, PROPERTYTYPE> {
	PROPERTYTYPE get(WRAPPERTYPE source);
	void set(WRAPPERTYPE destination, PROPERTYTYPE value);
}
