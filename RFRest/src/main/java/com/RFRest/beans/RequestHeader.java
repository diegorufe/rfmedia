package com.RFRest.beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Limit;
import com.RFData.beans.Order;

/**
 * 
 * @author diego
 *
 */
public class RequestHeader<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6873426551601664512L;

	private LinkedList<Fetch> fetchs;
	private LinkedList<Filter> filters;
	private LinkedList<Order> orders;
	private T data;
	private Limit limit;
	private Map<String, Object> params;

	public RequestHeader() {

	}

	public RequestHeader(LinkedList<Fetch> fetchs, LinkedList<Filter> filters, LinkedList<Order> orders, T data,
			Limit limit) {
		super();
		this.fetchs = fetchs;
		this.filters = filters;
		this.orders = orders;
		this.data = data;
		this.limit = limit;
	}

	public RequestHeader(LinkedList<Fetch> fetchs, LinkedList<Filter> filters, LinkedList<Order> orders, T data,
			Limit limit, Map<String, Object> params) {
		this(fetchs, filters, orders, data, limit);
		this.params = params;
	}

	public LinkedList<Filter> getFilters() {
		return filters;
	}

	public void setFilters(LinkedList<Filter> filters) {
		this.filters = filters;
	}

	public LinkedList<Order> getOrders() {
		return orders;
	}

	public void setOrders(LinkedList<Order> orders) {
		this.orders = orders;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

	public LinkedList<Fetch> getFetchs() {
		return fetchs;
	}

	public void setFetchs(LinkedList<Fetch> fetchs) {
		this.fetchs = fetchs;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
