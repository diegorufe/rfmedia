package com.RFData.beans;

import java.io.Serializable;
/**
 * 
 * @author diego
 *
 */
public class Fetch  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7918659355807665528L;
	
	private String type;
	private String value;
	
	public Fetch() {
	}
	
	
	public Fetch(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
	

}
