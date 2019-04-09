package com.RFData.constants;
/**
 * 
 * @author diego
 *
 */
public enum EnumFetchs {
	INNER("INNER"),
	
	LEFT("LEFT"),
	
	RIGTH("RIGTH");
	
	private String value;
	
	private EnumFetchs(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static EnumFetchs convert(String value) {
		EnumFetchs[] enumData = EnumFetchs.values();
		for (EnumFetchs enumO : enumData) {
			if(enumO.getValue().equals(value.trim())) {
				return enumO;
			}
		}
		return null;
	}
}
