package com.RFRest.controller.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.RFCore.utils.collection.UtilsCollection;
import com.RFCore.utils.reflection.UtilsReflection;
import com.RFData.beans.Limit;
import com.RFData.constants.EnumResponseCode;
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
	@RequestMapping(value = IConstantsRest.URL_LIST, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse<List<T>>> list(@RequestBody RequestHeader<T> requestHeader) {
		List<T> dataRes = null;
		if (requestHeader != null) {

			dataRes = getService().find(requestHeader.getFetchs(), requestHeader.getFilters(),
					requestHeader.getOrders(), requestHeader.getLimit());
		}
		return new ResponseEntity<RequestResponse<List<T>>>(new RequestResponse<List<T>>(dataRes, null), HttpStatus.OK);
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
	@RequestMapping(value = IConstantsRest.URL_EDIT, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse<T>> edit(@RequestBody RequestHeader<T> requestHeader) throws Exception {
		T entity = null;
		if (requestHeader != null) {
			entity = (T) requestHeader.getData();
		}
		if (entity != null) {
			UtilsReflection.resolveAsociations(entity);
			entity = this.getService().update(entity).getEntity();
		} else {
			throw new Exception();
		}
		return new ResponseEntity<RequestResponse<T>>(new RequestResponse<T>(entity, null), HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_ADD, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse<T>> add(@RequestBody RequestHeader<T> requestHeader) throws Exception {
		T entity = null;
		if (requestHeader != null) {
			entity = (T) requestHeader.getData();
		}
		if (entity != null) {
			UtilsReflection.resolveAsociations(entity);
			entity = this.getService().save(entity).getEntity();
		} else {
			throw new Exception();
		}
		return new ResponseEntity<RequestResponse<T>>(new RequestResponse<T>(entity, null), HttpStatus.CREATED);
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_DELETE, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse> delete(@RequestBody RequestHeader<T> requestHeader) throws Exception {
		T entity = null;
		if (requestHeader != null) {
			entity = (T) requestHeader.getData();
		}
		if (entity != null) {
			if (this.getService().delete(entity).getCodeResponse() != EnumResponseCode.OK.getValue()) {
				throw new Exception("Error delete");
			}
		} else {
			throw new Exception();
		}
		return new ResponseEntity<RequestResponse>(new RequestResponse(null, null), HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_READ, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse<T>> read(@RequestBody RequestHeader<T> requestHeader) {
		List<T> dataRes = null;
		T entity = null;
		if (requestHeader != null) {
			Limit limit = new Limit(0, 1);
			dataRes = getService().find(requestHeader.getFetchs(), requestHeader.getFilters(), null, limit);
			if (UtilsCollection.isListNotNull(dataRes)) {
				entity = dataRes.get(0);
			}
		}
		return new ResponseEntity<RequestResponse<T>>(new RequestResponse<T>(entity, null), HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getGenericClass() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[0];
	}

	@Override
	@RequestMapping(value = IConstantsRest.URL_LAOD_NEW, method = RequestMethod.POST)
	public ResponseEntity<RequestResponse<T>> loadNew(@RequestBody RequestHeader<T> requestHeader)
			throws InstantiationException, IllegalAccessException {
		T entity = this.getService().loadNew(null);
		return new ResponseEntity<RequestResponse<T>>(new RequestResponse<T>(entity, null), HttpStatus.OK);
	}

}
