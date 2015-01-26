package com.elsevier.json.wrapper;

import java.util.Arrays;


/**
 * Instructions on what a given method means in terms of choosing invocation strategy
 */
public class InvocationDefinition {
	// a request to wrap as a sub object properties of the parent
	boolean isNormalised;
	
	// the field name in the parent that is being turned into a property
	String[] fieldPath;
	
	// is the method a getter/setter
	boolean isGetter;
	boolean isSetter;
	
	boolean isPresented;
	
	// what type and generic type does this invocation trade in for get/set
	Class<?> propertyType;
	Class<?> propertyGenericType;
	
	@SuppressWarnings("rawtypes")
	Class<? extends Presenter> presenterClass;
	
	boolean isProperty() {
		return (isGetter || isSetter) && !isPresented;
	}

	public boolean hasFieldPath() {
		return fieldPath!=null && fieldPath.length>1;
	}

	/**
	 * @return the path leading up to the field leaf node
	 */
	public String[] getPath() {
		return Arrays.copyOfRange(fieldPath, 0, fieldPath.length-1);
	}

	/**
	 * @return the name of the field leaf node
	 */
	public String getFieldName() {
		return fieldPath!=null && fieldPath.length>0 ? fieldPath[fieldPath.length-1] : null;
	}

	public boolean isPresented() {
		return isPresented;
	}

	@SuppressWarnings("rawtypes")
	public Class<? extends Presenter> getPresenterClass() {
		return presenterClass;
	}
}
