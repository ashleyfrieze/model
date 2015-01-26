package com.elsevier.vtw.core.model.wrapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This declares that a wrapper for a sub object is actually wrapping not a field within
 * the parent but the very self-same parent
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Normalised {

}
