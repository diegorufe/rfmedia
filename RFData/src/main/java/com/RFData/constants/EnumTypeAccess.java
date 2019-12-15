package com.RFData.constants;

import com.RFCore.utils.string.UtilsString;

/**
 * Enum types for access
 * 
 * @author diego
 *
 */
public enum EnumTypeAccess {

	LIST("LIST"),

	READ("READ"),

	ADD("ADD"),

	EDIT("EDIT"),

	DELETE("DELTE"),

	REPORT("REPORT"),

	OTHERS("OTHERS"),

	;

	private String value;

	private EnumTypeAccess(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public EnumTypeAccess convert(String value) {
		EnumTypeAccess data = EnumTypeAccess.LIST;
		if (!UtilsString.isBlank(value)) {
			for (EnumTypeAccess type : values()) {
				if (type.getValue().equalsIgnoreCase(value)) {
					data = type;
					break;
				}
			}
		}
		return data;
	}
}
