package com.RFData.beans;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5077075809230860093L;
	private String field;
	private String type;

	public Order() {
	}

	public Order(String field, String type) {
		super();
		this.field = field;
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
