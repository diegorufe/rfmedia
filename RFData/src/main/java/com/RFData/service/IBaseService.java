package com.RFData.service;

import java.util.LinkedList;
import java.util.List;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Order;
import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK> is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *        {@link #BaseCoreEntity}
 */
public interface IBaseService<T extends BaseCoreEntity> {

	@SuppressWarnings("rawtypes")
	public IBaseDao getDao();

	@SuppressWarnings("rawtypes")
	public void setDao(IBaseDao baseDao);

	public T save(T entidad);

	public T update(T entidad);

	public void delete(T entidad);

	public int count(LinkedList<Filter> filters);

	public List<T> find(LinkedList<Fetch> fetchs, LinkedList<Filter> filters, LinkedList<Order> orders, int... limits);
	
	public List<T> findAll();
	
	public T loadNew() throws InstantiationException, IllegalAccessException;
}
