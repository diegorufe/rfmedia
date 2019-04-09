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
public abstract class BaseServiceImpl<T extends BaseCoreEntity> implements IBaseService<T> {

	@SuppressWarnings("rawtypes")
	@Override
	public abstract IBaseDao getDao();

	@SuppressWarnings("rawtypes")
	@Override
	public abstract void setDao(IBaseDao dao);

	@SuppressWarnings("unchecked")
	@Override
	public T save(T entidad) {
		return (T) getDao().save(entidad);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T update(T entidad) {
		return (T) getDao().save(entidad);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getDao().findAll();
	}
	
	@SuppressWarnings("unchecked")
	public T loadNew() throws InstantiationException, IllegalAccessException {
		return (T) this.getDao().getClasegenerica().newInstance();
	}

}
