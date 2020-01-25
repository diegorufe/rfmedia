package com.RFCoreSecurity.constants;

/**
 * 
 * @author diego
 *
 */
public enum EnumParamOptionsSecurity {
	PARAM_SECURITY_USER_ID("securityUserId"),

	UNDEFINED(""),

	;

	private String value;

	private EnumParamOptionsSecurity(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
