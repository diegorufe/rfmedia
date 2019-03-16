package com.RFData.constants;
/**
 * 
 * @author diego
 *
 */
public enum EnumConditionFilter {
	LIKE("LIKE"),
	
	IGUAL("="),
	
	MENOR_IGUAL("<="),
	
	MENOR("<"),
	
	DISTINTO("!="),
	
	MAYOR_IGUAL(">="),
	
	MAYOR(">");
	
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
			if(enumO.getValue().equals(value.trim())) {
				return enumO;
			}
		}
		return null;
	}
}
