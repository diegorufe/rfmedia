package com.RFSecurity.beans;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class Performance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3083799459301359302L;

	private int id;
	private String code;

	public Performance() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
