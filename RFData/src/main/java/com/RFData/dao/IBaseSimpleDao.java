package com.RFData.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
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
import com.RFData.constants.EnumOperatorFilter;
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
	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";

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
			fixFiltersInCriteria(criteriaQuery, criteriaBuilder, root, filters);
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
				if (order != null && !order.getType().toUpperCase().equals(EnumOrders.NONE.getValue().toUpperCase())) {
					if (order.getType().toUpperCase().equals(EnumOrders.ASC.getValue().toUpperCase())) {
						orderList.add(criteriaBuilder.asc(root.get(order.getField())));
					} else if (order.getType().toUpperCase().equals(EnumOrders.DESC.getValue().toUpperCase())) {
						orderList.add(criteriaBuilder.desc(root.get(order.getType())));
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
	@SuppressWarnings("rawtypes")
	public default Predicate fixFiltersInCriteria(CriteriaQuery criteria, CriteriaBuilder builder, Root<T> root,
			List<Filter> filters) {
		return this.fixFiltersInCriteria(criteria, builder, root, filters, null);
	}

	/**
	 * Method to set filters in criteria query
	 * 
	 * @param criteria
	 * @param builder
	 * @param root
	 * @param filters
	 * @param parentPredicate
	 */
	@SuppressWarnings({ "rawtypes" })
	public default Predicate fixFiltersInCriteria(CriteriaQuery criteria, CriteriaBuilder builder, Root<T> root,
			List<Filter> filters, Predicate parentPredicate) {
		if (filters != null) {
			List<Predicate> restrinctions = new ArrayList<Predicate>();
			Expression expresion = null;
			// Expression<Date> campoFecha = null;
			// Date fecha1 = null;
			// Date fecha2 = null;
			Join otherEntity = null;
			String[] splitValues = null;
			Object value = null;
			Predicate predicate = null;
			for (Filter filter : filters) {
				otherEntity = null;
				value = filter.getValue();
				predicate = null;
				if (filter.getCondition() != null && value != null) {

					splitValues = filter.getField().split("\\.");

					if (splitValues != null && splitValues.length > 1) {
						otherEntity = root.join(splitValues[0].trim(), JoinType.LEFT);
					}

					if (value != null && value instanceof Map) {
						value = ((Map) value).get(splitValues[1].trim());
					}

					if (otherEntity != null) {
						expresion = otherEntity.get(splitValues[1].trim());
					} else {
						expresion = root.get(filter.getField());
					}

					predicate = this.getPredicateConditionCriteria(builder, filter.getOperator(), filter.getCondition(),
							expresion, filter.getValue());

				}

				if (predicate == null && filter.getFilters() != null && filter.getFilters().size() > 0) {
					predicate = this.getPredicateConditionCriteria(builder, filter.getOperator(),
							EnumConditionFilter.FILTERS.getValue(), null, true);
				}

				if (filter.getFilters() != null && filter.getFilters().size() > 0) {
					this.fixFiltersInCriteria(criteria, builder, root, filter.getFilters(), predicate);
				}

				if (parentPredicate != null) {
					parentPredicate.getExpressions().add(predicate);
				} else {
					restrinctions.add(predicate);
				}

			}

			if (parentPredicate == null) {
				criteria.where(restrinctions.toArray(new Predicate[restrinctions.size()]));
			}
		}
		return parentPredicate;
	}

	@SuppressWarnings("unchecked")
	public default Class<T> getGenericClass() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[1];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public default Predicate getPredicateConditionCriteria(CriteriaBuilder builder, String operator, String condition,
			Expression expresion, Object value) {
		Predicate predicate = null;

		if (value != null) {
			switch (EnumConditionFilter.convert(condition)) {

			case DISJUNCTION:
				predicate = builder.disjunction();
				break;

			case EQUAL:
				predicate = builder.equal(expresion, value);
				break;

			case FILTERS:
				predicate = builder.equal(builder.literal(1), 1);
				break;

			case GE:
				if (value instanceof String) {
					predicate = builder.greaterThan(expresion, (String) value);
				} else {
					predicate = builder.ge(expresion, (Expression<? extends Number>) value);
				}
				break;

			case GT:
				if (value instanceof String) {
					predicate = builder.greaterThanOrEqualTo(expresion, (String) value);
				} else {
					predicate = builder.gt(expresion, (Number) value);
				}
				break;

			case IN:
				predicate = expresion.in(value);
				break;

			case IS_NOT_NULL:
				predicate = expresion.isNotNull();
				break;

			case IS_NULL:
				predicate = expresion.isNull();
				break;

			case NOT_IN:
				predicate = expresion.in(value).not();
				break;

			case LIKE:
				predicate = builder.like(expresion, "%" + ((String) value).trim().toUpperCase() + "%");
				break;

			case LE:
				if (value instanceof String) {
					predicate = builder.lessThan(expresion, (String) value);
				} else {
					predicate = builder.le(expresion, (Number) value);
				}
				break;

			case LT:
				if (value instanceof String) {
					predicate = builder.lessThanOrEqualTo(expresion, (String) value);
				} else {
					predicate = builder.lt(expresion, (Number) value);
				}
				break;

			default:
				break;
			}
		}

		if (predicate != null) {
			predicate = this.getPredicateOperatorCriteria(builder, operator, predicate);
		}

		return predicate;
	}

	public default Predicate getPredicateOperatorCriteria(CriteriaBuilder builder, String operator,
			Predicate predicateCondition) {

		if (operator != null) {
			switch (EnumOperatorFilter.convert(operator)) {
			case AND:
				predicateCondition = builder.and(predicateCondition);
				break;
			case OR:
				predicateCondition = builder.or(predicateCondition);
				break;
			}
		}

		return predicateCondition;
	}

}
