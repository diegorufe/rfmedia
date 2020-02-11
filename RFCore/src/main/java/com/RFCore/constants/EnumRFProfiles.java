package com.RFCore.constants;

/**
 * Types of profiles
 * 
 * @author diego
 *
 */
public enum EnumRFProfiles {
	DEV("Dev"),

	PROD("Prod"),

	TEST("Test"),

	DEV_1("Dev1"),

	PROD_1("Prod1"),

	TEST_1("Test1"),

	DEV_2("Dev2"),

	PROD_2("Prod2"),

	TEST_2("Test2"),

	ANY("any"),

	;

	private String value;

	private EnumRFProfiles(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static EnumRFProfiles convert(String value) {
		EnumRFProfiles enumProfile = EnumRFProfiles.DEV;
		for (EnumRFProfiles enumProfileFind : values()) {
			if (enumProfileFind.getValue().equals(value)) {
				enumProfile = enumProfileFind;
				break;
			}
		}
		return enumProfile;
	}
}
