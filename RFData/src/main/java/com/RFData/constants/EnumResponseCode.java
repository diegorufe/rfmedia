package com.RFData.constants;

/**
 * 
 * @author diego
 *
 */
public enum EnumResponseCode {
	// Success
	OK(0x00000000),

	// Error
	KO(0xE0000000),

	;

	private EnumResponseCode(int value) {
		this.value = value;
	}

	private int value;

	public int getValue() {
		return value;
	}

	public EnumResponseCode convert(int value) {
		EnumResponseCode returnValue = EnumResponseCode.KO;
		for (EnumResponseCode enumResponseCode : values()) {
			if (enumResponseCode.getValue() == value) {
				returnValue = enumResponseCode;
				break;
			}
		}
		return returnValue;
	}
}
