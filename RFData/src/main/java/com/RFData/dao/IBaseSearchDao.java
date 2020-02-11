package com.RFData.dao;

import java.util.List;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Limit;
import com.RFData.beans.Order;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>
 * @param <T>
 */
public interface IBaseSearchDao<T extends BaseCoreEntity, PK> extends IBaseSimpleDao<T, PK> {

	/**
	 * Mhetod to find all
	 * 
	 * @return
	 */
	public List<T> findAll();

	/**
	 * Mhetod to count by filters query
	 * 
	 * @param filters
	 * @return
	 */
	public int count(List<Filter> filters);

	/**
	 * Method to find
	 * 
	 * @param fetchs
	 * @param filters
	 * @param orders
	 * @param limits
	 * @return
	 */
	public List<T> find(List<Fetch> fetchs, List<Filter> filters, List<Order> orders, Limit limit);
}
