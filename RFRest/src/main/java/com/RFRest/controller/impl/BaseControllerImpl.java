package com.RFRest.controller.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.RFCore.utils.UtilsFields;
import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.service.IBaseService;
import com.RFRest.beans.RequestHeader;
import com.RFRest.beans.RequestResponse;
import com.RFRest.constants.IConstantsRest;
import com.RFRest.controller.IBaseController;

/**
 * 
 * @author diego
 *
 * @param <T>
 */
public abstract class BaseControllerImpl<SERVICE extends IBaseService<DAO, T, PK>, DAO extends IBaseDao<PK, T>, T extends BaseCoreEntity, PK>
		implements IBaseController<SERVICE, DAO, T, PK> {

	@Autowired
	private SERVICE service;

	@Override
	public SERVICE getService() {
		return this.service;
	}

	@Override
	public void setService(SERVICE service) {
		this.service = service;
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_FIND, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse> find(@RequestBody RequestHeader<T> requestHeader) {
		List<T> dataRes = null;
		if (requestHeader != null) {

			dataRes = getService().find(requestHeader.getFetchs(), requestHeader.getFilters(),
					requestHeader.getOrders(), requestHeader.getLimit());
		}
		return new ResponseEntity<RequestResponse>(new RequestResponse(dataRes, null), HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_COUNT, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse> count(@RequestBody RequestHeader<T> requestHeader) {
		int countValue = 0;
		if (requestHeader != null) {
			countValue = getService().count(requestHeader.getFilters());
		}
		return new ResponseEntity<RequestResponse>(new RequestResponse(countValue, null), HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_UPDATE, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse> update(@RequestBody RequestHeader<T> requestHeader) throws Exception {
		T entity = null;
		if (requestHeader != null) {
			entity = (T) requestHeader.getData();
		}
		if (entity != null) {
			UtilsFields.resolveAsociations(entity);
			entity = this.getService().update(entity);
		} else {
			throw new Exception();
		}
		return new ResponseEntity<RequestResponse>(new RequestResponse(entity, null), HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_INSERT, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse> insert(@RequestBody RequestHeader<T> requestHeader) throws Exception {
		T entity = null;
		if (requestHeader != null) {
			entity = (T) requestHeader.getData();
		}
		if (entity != null) {
			UtilsFields.resolveAsociations(entity);
			entity = this.getService().save(entity);
		} else {
			throw new Exception();
		}
		return new ResponseEntity<RequestResponse>(new RequestResponse(entity, null), HttpStatus.CREATED);
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_DELETE, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse> delete(@RequestBody RequestHeader<T> requestHeader) throws Exception {
		T entity = null;
		if (requestHeader != null) {
			entity = (T) requestHeader.getData();
		}
		if (entity != null) {
			this.getService().delete(entity);
		} else {
			throw new Exception();
		}
		return new ResponseEntity<RequestResponse>(new RequestResponse(null, null), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<RequestResponse> first(@RequestBody RequestHeader<T> requestHeader) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getGenericClass() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[0];
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_LAOD_NEW, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse> loadNew(@RequestBody RequestHeader<T> requestHeader)
			throws InstantiationException, IllegalAccessException {
		T entity = this.getService().loadNew();
		return new ResponseEntity<RequestResponse>(new RequestResponse(entity, null), HttpStatus.OK);
	}

}
