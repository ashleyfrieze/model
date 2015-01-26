package com.elsevier.vtw.core.model.wrapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to tag a property within an interface to show that it is a getter/setter of json values
 * and how to find the right json property in the json document for getting/setting
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Field {
	/**
	 * Which field within the JSON to map to the getter/setter
	 * if left blank then the field name is derived from the name of the getter/setter
	 */
	String value() default "";
	
	/**
	 * A path within the JSON to map the getter/setter to for flattening hierarchies
	 * if non blank then this takes precedence over the value
	 */
	String[] path() default {};
}
