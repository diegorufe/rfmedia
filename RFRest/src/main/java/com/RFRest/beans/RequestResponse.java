package com.RFRest.beans;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class RequestResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5212089258123802668L;

	private Object data;
	private String messageResponse;
	
	public RequestResponse() {
	}
	
	public RequestResponse(Object data, String messageResponse) {
		super();
		this.data = data;
		this.messageResponse = messageResponse;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}
	
}
