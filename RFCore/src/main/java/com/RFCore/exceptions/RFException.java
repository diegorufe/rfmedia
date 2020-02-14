package com.RFCore.exceptions;

/**
 * 
 * @author diego
 *
 */
public class RFException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7698893667397618687L;

	public RFException() {
		super();
	}

	public RFException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RFException(String arg0) {
		super(arg0);
	}

	public RFException(Throwable arg0) {
		super(arg0);
	}

}
