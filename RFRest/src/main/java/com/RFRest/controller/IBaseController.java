package com.RFRest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.service.IBaseService;
import com.RFRest.beans.RequestHeader;
import com.RFRest.beans.RequestResponse;

/**
 * 
 * @author diego
 *
 * @param <T>
 */
public interface IBaseController<SERVICE extends IBaseService<DAO, T, PK>, DAO extends IBaseDao<T, PK>, T extends BaseCoreEntity, PK> {

	public SERVICE getService();

	public void setService(SERVICE service);

	public ResponseEntity<RequestResponse<List<T>>> list(RequestHeader<T> requestHeader);

	public ResponseEntity<RequestResponse> count(RequestHeader<T> requestHeader);

	public ResponseEntity<RequestResponse<T>> edit(RequestHeader<T> requestHeader) throws Exception;

	public ResponseEntity<RequestResponse<T>> add(RequestHeader<T> requestHeader) throws Exception;

	public ResponseEntity<RequestResponse> delete(RequestHeader<T> requestHeader) throws Exception;

	public ResponseEntity<RequestResponse<T>> read(RequestHeader<T> requestHeader);

	public Class<T> getGenericClass();

	public ResponseEntity<RequestResponse<T>> loadNew(RequestHeader<T> requestHeader)
			throws InstantiationException, IllegalAccessException;

}
