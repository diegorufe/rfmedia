package com.RFRestCoreERP.comstants;

/**
 * 
 * @author diego
 *
 */
public enum EnumParamOptionsERP {
	PARAM_ERP_SELECTED_ENTERPRISE("erpSelectedEnterprise"),

	UNDEFINED(""),

	;

	private String value;

	private EnumParamOptionsERP(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
