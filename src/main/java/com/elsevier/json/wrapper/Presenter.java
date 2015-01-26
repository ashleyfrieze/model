package com.elsevier.json.wrapper;

public interface Presenter<WRAPPERTYPE, PROPERTYTYPE> {
	PROPERTYTYPE get(WRAPPERTYPE source);
	void set(WRAPPERTYPE destination, PROPERTYTYPE value);
}
