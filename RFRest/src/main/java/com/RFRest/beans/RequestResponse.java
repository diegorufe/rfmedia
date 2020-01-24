package com.RFRest.beans;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class RequestResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5212089258123802668L;

	private T data;
	private String messageResponse;
	
	public RequestResponse() {
	}
	
	public RequestResponse(T data, String messageResponse) {
		super();
		this.data = data;
		this.messageResponse = messageResponse;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessageResponse() {
		return messageResponse;
	}

	public void setMessageResponse(String messageResponse) {
		this.messageResponse = messageResponse;
	}
	
}
