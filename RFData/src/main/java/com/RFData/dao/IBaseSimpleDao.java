package com.RFData.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Order;
import com.RFData.constants.EnumConditionFilter;
import com.RFData.constants.EnumFetchs;
import com.RFData.constants.EnumOrders;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.event.IAuditEventFactory;

/**
 * 
 * @author diego
 *
 * @param <PK>
 * @param <T>
 */
public interface IBaseSimpleDao<PK, T extends BaseCoreEntity> {

	public static final String ID = "id";
	public static final String CODE = "codigo";
	public static final String DESCRIPTION = "descripcion";

	public EntityManager getEntityManager();

	public IAuditEventFactory getAuditEventFactory();

	public void setAuditEventFactory(IAuditEventFactory auditEventFactory);

	/**
	 * Method to create query by fetch, filters and orders
	 * 
	 * @param fetchs
	 * @param filters
	 * @param orders
	 * @return
	 */
	public default CriteriaQuery<T> createQuery(List<Fetch> fetchs, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getGenericClass());
		Root<T> root = criteriaQuery.from(getGenericClass());
		if (fetchs != null && fetchs.size() > 0) {
			setFetchsInCriteria(criteriaQuery, criteriaBuilder, root, fetchs);
		}
		if (filters != null && filters.size() > 0) {
			setFiltersInCriteria(criteriaQuery, criteriaBuilder, root, filters);
		}
		List<javax.persistence.criteria.Order> orderList = this.getOrdersCriteriaQuery(criteriaBuilder, root, orders);
		if (orderList != null && orderList.size() > 0) {
			criteriaQuery.orderBy(orderList);
		}
		return criteriaQuery;
	}

	/**
	 * Method to generate orders for criteria query
	 * 
	 * @param criteriaBuilder
	 * @param root
	 * @param orders
	 * @return
	 */
	public default List<javax.persistence.criteria.Order> getOrdersCriteriaQuery(CriteriaBuilder criteriaBuilder,
			Root<T> root, List<Order> orders) {
		List<javax.persistence.criteria.Order> orderList = new ArrayList<javax.persistence.criteria.Order>();
		if (orders != null && orders.size() > 0) {
			for (Order order : orders) {
				if (order != null && !order.getTipo().toUpperCase().equals(EnumOrders.NONE.getValue().toUpperCase())) {
					if (order.getTipo().toUpperCase().equals(EnumOrders.ASC.getValue().toUpperCase())) {
						orderList.add(criteriaBuilder.asc(root.get(order.getCampo())));
					} else if (order.getTipo().toUpperCase().equals(EnumOrders.DESC.getValue().toUpperCase())) {
						orderList.add(criteriaBuilder.desc(root.get(order.getCampo())));
					}
				}
			}
		}
		return orderList;
	}

	/**
	 * Mehtod to set fech in criteria query
	 * 
	 * @param criteria
	 * @param builder
	 * @param root
	 * @param fetchs
	 */
	@SuppressWarnings("rawtypes")
	public default void setFetchsInCriteria(CriteriaQuery criteria, CriteriaBuilder builder, Root<T> root,
			List<Fetch> fetchs) {
		EnumFetchs fechType = null;
		if (fetchs != null) {
			for (Fetch fetch : fetchs) {
				fechType = EnumFetchs.convert(fetch.getType());
				switch (fechType) {
				case INNER:
					root.fetch(fetch.getValue(), JoinType.INNER);
					break;
				case LEFT:
					root.fetch(fetch.getValue(), JoinType.LEFT);
					break;
				case RIGTH:
					root.fetch(fetch.getValue(), JoinType.RIGHT);
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * Method to set filters in criteria query
	 * 
	 * @param criteria
	 * @param builder
	 * @param root
	 * @param filters
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public default void setFiltersInCriteria(CriteriaQuery criteria, CriteriaBuilder builder, Root<T> root,
			List<Filter> filters) {
		if (filters != null) {
			LinkedList<Predicate> restrinctions = new LinkedList<Predicate>();
			Expression expresion = null;
			// Expression<Date> campoFecha = null;
			// Date fecha1 = null;
			// Date fecha2 = null;
			Join otherEntity = null;
			String[] splitValues = null;
			Object valor = null;
			for (Filter filter : filters) {
				otherEntity = null;
				valor = filter.getValor();
				if (filter.getCondicion() != null && valor != null) {
					splitValues = filter.getCampo().split("\\.");
					if (splitValues != null && splitValues.length > 1) {
						otherEntity = root.join(splitValues[0].trim(), JoinType.LEFT);
					}
					if (valor != null && valor instanceof Map) {
						valor = ((Map) valor).get(splitValues[1].trim());
					}
					if (otherEntity != null) {
						expresion = otherEntity.get(splitValues[1].trim());
					} else {
						expresion = root.get(filter.getCampo());
					}

					if (valor != null) {
						switch (EnumConditionFilter.convert(filter.getCondicion())) {
						case LIKE:
							restrinctions
									.add(builder.like(expresion, "%" + ((String) valor).trim().toUpperCase() + "%"));
							break;
						case IGUAL:
							restrinctions.add(builder.equal(expresion, valor));
							break;
						default:
							break;
						}
					}
				}
			}
			criteria.where(restrinctions.toArray(new Predicate[restrinctions.size()]));
		}
	}

	@SuppressWarnings("unchecked")
	public default Class<T> getGenericClass() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[1];
	}

}
