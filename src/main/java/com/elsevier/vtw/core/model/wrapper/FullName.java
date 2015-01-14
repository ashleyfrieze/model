package com.elsevier.vtw.core.model.wrapper;

import com.elsevier.vtw.core.model.wrapper.internal.CompositeHandler;

public class FullName implements CompositeHandler<CompositeProperties, String> {

	@Override
	public String get(CompositeProperties source) {
		return source.getFirstName() + " " + source.getLastName();
	}

	@Override
	public void set(CompositeProperties destination, String value) {
		String[] split = value.split(" ");
		destination.setFirstName(split[0]);
		destination.setLastName(split[1]);
	}

}
