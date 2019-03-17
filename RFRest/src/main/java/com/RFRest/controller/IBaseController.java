package com.RFRest.controller;

import org.springframework.http.ResponseEntity;

import com.RFData.entities.BaseCoreEntity;
import com.RFRest.beans.RequestHeader;
import com.RFRest.beans.RequestResponse;

/**
 * 
 * @author diego
 *
 * @param <T>
 */
public interface IBaseController<T extends BaseCoreEntity> {

	public ResponseEntity<RequestResponse> find(RequestHeader<T> requestHeader);

	public ResponseEntity<RequestResponse> count(RequestHeader<T> requestHeader);

	public ResponseEntity<RequestResponse> update(RequestHeader<T> requestHeader) throws Exception;

	public ResponseEntity<RequestResponse> insert(RequestHeader<T> requestHeader) throws Exception;

	public ResponseEntity<RequestResponse> delete(RequestHeader<T> requestHeader) throws Exception;

	public ResponseEntity<RequestResponse> first(RequestHeader<T> requestHeader);

	public Class<T> getClasegenerica();
}
