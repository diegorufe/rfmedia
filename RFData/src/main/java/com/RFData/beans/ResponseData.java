package com.RFData.beans;

/**
 * 
 * @author diego
 *
 */
public class ResponseData<T> {

	private T entity;
	private int codeResponse;
	private Object extraDataResponse;

	public ResponseData() {
	}

	public ResponseData(T entity) {
		super();
		this.entity = entity;
	}

	public ResponseData(T entity, int codeResponse) {
		super();
		this.entity = entity;
		this.codeResponse = codeResponse;
	}

	public ResponseData(T entity, int codeResponse, Object extraDataResponse) {
		super();
		this.entity = entity;
		this.codeResponse = codeResponse;
		this.extraDataResponse = extraDataResponse;
	}

	public ResponseData(int codeResponse) {
		super();
		this.codeResponse = codeResponse;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public int getCodeResponse() {
		return codeResponse;
	}

	public void setCodeResponse(int codeResponse) {
		this.codeResponse = codeResponse;
	}

	public Object getExtraDataResponse() {
		return extraDataResponse;
	}

	public void setExtraDataResponse(Object extraDataResponse) {
		this.extraDataResponse = extraDataResponse;
	}

}
