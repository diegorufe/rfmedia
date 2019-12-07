package com.RFData.constants;

/**
 * 
 * @author diego
 *
 */
public enum EnumOperatorFilter {
	AND("and"),

	OR("or"),

	;

	private String value;

	private EnumOperatorFilter(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static EnumOperatorFilter convert(String value) {
		EnumOperatorFilter[] enumData = EnumOperatorFilter.values();
		for (EnumOperatorFilter enumO : enumData) {
			if (enumO.getValue().equals(value.trim())) {
				return enumO;
			}
		}
		return null;
	}
}
