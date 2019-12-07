package com.RFCore.beans.certified;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6541160726149968871L;

	private String key;
	private String text = "";

	public Answer() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
