package com.elsevier.vtw.core.model.wrapper.internal;

import com.elsevier.vtw.core.model.wrapper.CompositeProperties;

public interface CompositeHandler<WRAPPERTYPE, PROPERTYTYPE> {
	PROPERTYTYPE get(WRAPPERTYPE source);
	void set(WRAPPERTYPE destination, PROPERTYTYPE value);
}
