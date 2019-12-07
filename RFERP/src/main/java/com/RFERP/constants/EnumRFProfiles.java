package com.RFERP.constants;

/**
 * Types of profiles
 * 
 * @author diego
 *
 */
public enum EnumRFProfiles {
	DEV("Dev"),

	PROD("Prod"),

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
