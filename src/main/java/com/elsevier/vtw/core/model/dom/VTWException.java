package com.elsevier.vtw.core.model.dom;

public class VTWException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	
	private static final long serialVersionUID = -6812240116282804493L;

	/**
	 * Creates a new VTWException object.
	 */
	public VTWException() {
		super();
	}

	/**
	 * Creates a new VTWException object.
	 * 
	 * @param message
	 *            Error message
	 */
	public VTWException(final String message) {
		super(message);
	}

	/**
	 * Creates a new VTWException object.
	 * 
	 * @param cause
	 *            Throwable cause
	 */
	public VTWException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new VTWException object.
	 * 
	 * @param message
	 *            Error message
	 * @param cause
	 *            Throwable cause
	 */
	public VTWException(final String message,final Throwable cause) {
		super(message, cause);
	}

	
	
	
}
