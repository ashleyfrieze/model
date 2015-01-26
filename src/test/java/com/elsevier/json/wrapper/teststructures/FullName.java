package com.elsevier.json.wrapper.teststructures;

import com.elsevier.json.wrapper.Presenter;

public class FullName implements Presenter<PresentedProperties, String> {

	@Override
	public String get(PresentedProperties source) {
		return source.getFirstName() + " " + source.getLastName();
	}

	@Override
	public void set(PresentedProperties destination, String value) {
		String[] split = value.split(" ");
		destination.setFirstName(split[0]);
		destination.setLastName(split[1]);
	}

}
