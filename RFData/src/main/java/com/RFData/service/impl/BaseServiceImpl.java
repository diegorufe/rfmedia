package com.RFData.service.impl;

import java.util.LinkedList;
import java.util.List;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Order;
import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.service.IBaseService;

/**
 * 
 * @author diego
 *
 */
public class BaseServiceImpl<T extends BaseCoreEntity> implements IBaseService<T> {

	@SuppressWarnings("rawtypes")
	private IBaseDao dao;

	@SuppressWarnings("rawtypes")
	@Override
	public IBaseDao getDao() {
		return this.dao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setDao(IBaseDao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T save(T entidad) {
		return (T) getDao().save(entidad);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T update(T entidad) {
		return (T) getDao().update(entidad);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(T entidad) {
		getDao().delete(entidad);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int count(LinkedList<Filter> filters) {
		return getDao().count(filters);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(LinkedList<Fetch> fetchs, LinkedList<Filter> filters, LinkedList<Order> orders, int... limits) {
		return getDao().find(fetchs, filters, orders, limits);
	}

}
