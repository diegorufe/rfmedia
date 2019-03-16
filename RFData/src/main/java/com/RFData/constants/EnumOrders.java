package com.RFData.constants;
/**
 * 
 * @author diego
 *
 */
public enum EnumOrders {
	ASC("asc"),
	
	DESC("desc"),
	
	NONE("nonde");
	
	private String value;
	
	private EnumOrders(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static EnumOrders convert(String value) {
		EnumOrders[] enumData = EnumOrders.values();
		for (EnumOrders enumO : enumData) {
			if(enumO.getValue().equals(value.trim())) {
				return enumO;
			}
		}
		return null;
	}
}
