package com.RFData.service;

import java.util.List;
import java.util.Map;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Limit;
import com.RFData.beans.Order;
import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>             is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *                         {@link #BaseCoreEntity}
 */
public interface IBaseSearchService<DAO extends IBaseDao<T, PK>, T extends BaseCoreEntity, PK>
		extends IBaseSimpleService<DAO, T, PK> {

	public default int count(List<Filter> filters) {
		return getDao().count(filters);
	}

	public default List<T> find(List<Fetch> fetchs, List<Filter> filters, List<Order> orders, Limit limit,
			Map<String, Object> params) {
		return getDao().find(fetchs, filters, orders, limit);
	}

	public default List<T> findAll(Map<String, Object> params) {
		return getDao().findAll();
	}
}
