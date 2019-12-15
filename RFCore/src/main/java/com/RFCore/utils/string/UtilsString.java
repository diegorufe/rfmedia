package com.RFCore.utils.string;

/**
 * Class for utilities for strings
 * 
 * @author diego
 *
 */
public class UtilsString {

	/**
	 * Method to known string is blank
	 * 
	 * @param value to check
	 * @return true if blank or null false if not null and not empty
	 */
	public static boolean isBlank(String value) {
		return value == null || value.trim().isEmpty();
	}
}
