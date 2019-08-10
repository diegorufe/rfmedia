package com.RFData.constants;

/**
 * 
 * @author diego
 *
 */
public enum EnumConditionFilter {
	LIKE("LIKE"),

	EQUAL("="),

	LT("<"),

	LE("<="),

	DISTINCT("!="),

	GT(">="),

	GE(">"),

	DISJUNCTION("disjunction"),

	IN("in"),

	NOT_IN("not in"),

	IS_NULL("is null"),

	IS_NOT_NULL("is not null"),

	;

	private String value;

	private EnumConditionFilter(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static EnumConditionFilter convert(String value) {
		EnumConditionFilter[] enumData = EnumConditionFilter.values();
		for (EnumConditionFilter enumO : enumData) {
			if (enumO.getValue().equalsIgnoreCase(value.trim())) {
				return enumO;
			}
		}
		return null;
	}
}
