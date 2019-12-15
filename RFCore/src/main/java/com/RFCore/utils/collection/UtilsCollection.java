package com.RFCore.utils.collection;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author diego
 *
 */
public class UtilsCollection {

	/**
	 * Method to check map is not null and size > 0
	 * 
	 * @param mapToCheck
	 * @return true if not null and size > 0
	 */
	public static boolean isMapNotNull(Map mapToCheck) {
		return mapToCheck != null && mapToCheck.size() > 0;
	}

	/**
	 * Method to check list is not null and size > 0
	 * 
	 * @param listToCheck
	 * @return true if not null and size > 0
	 */
	public static boolean isListNotNull(List listToCheck) {
		return listToCheck != null && listToCheck.size() > 0;
	}
}
