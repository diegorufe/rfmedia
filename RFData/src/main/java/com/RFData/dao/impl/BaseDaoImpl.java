package com.RFData.dao.impl;
/**
 * 
 * @author diego
 *
 */

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.event.IAuditEventFactory;

/**
 * 
 * @author diego
 *
 * @param <PK> is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *        {@link #BaseCoreEntity}
 */
public abstract class BaseDaoImpl<PK, T extends BaseCoreEntity> implements IBaseDao<PK, T> {

	@PersistenceContext
	private EntityManager entityManager;
	private IAuditEventFactory auditEventFactory;

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public IAuditEventFactory getAuditEventFactory() {
		return this.auditEventFactory;
	}

	@Override
	public void setAuditEventFactory(IAuditEventFactory auditEventFactory) {
		this.auditEventFactory = auditEventFactory;
	}

	@Override
	public T save(T entidad) {
		getEntityManager().persist(entidad);
		return entidad;
	}

	@Override
	public T update(T entidad) {
		return getEntityManager().merge(entidad);
	}

	@Override
	public void delete(T entidad) {
		getEntityManager().remove(getEntityManager().contains(entidad) ? entidad : getEntityManager().merge(entidad));
	}

	@Override
	public List<T> findAll() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getClasegenerica());
		Root<T> root = criteriaQuery.from(getClasegenerica());
		CriteriaQuery<T> all = criteriaQuery.select(root);
		TypedQuery<T> allQuery = this.getEntityManager().createQuery(all);
		return allQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getClasegenerica() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[1];
	}

	@Override
	public int count(LinkedList<Filter> filters) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(getClasegenerica());
		criteriaQuery.select(criteriaBuilder.count(root));
		setFiltrosInCriteria(criteriaQuery, criteriaBuilder, root, filters);
		Long result = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		return result != null ? result.intValue() : 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(LinkedList<Fetch> fetchs, LinkedList<Filter> filters, LinkedList<Order> orders, int... limits) {
		List<T> data = null;
		Query query = getEntityManager().createQuery(createQuery(fetchs, filters, orders));
		if (limits != null && limits.length > 0 && limits.length <= 2) {
			query.setFirstResult(limits[0]);
			query.setMaxResults(limits[1]);
		}
		try {
			data = query.getResultList();
		} catch (Exception e) {
			data = null;
		}
		return data;
	}

	@Override
	public CriteriaQuery<T> createQuery(LinkedList<Fetch> fetchs, LinkedList<Filter> filters,
			LinkedList<Order> orders) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getClasegenerica());
		Root<T> root = criteriaQuery.from(getClasegenerica());
		if (fetchs != null && fetchs.size() > 0) {
			setFetchsInCriteria(criteriaQuery, criteriaBuilder, root, fetchs);
		}
		if (filters != null && filters.size() > 0) {
			setFiltrosInCriteria(criteriaQuery, criteriaBuilder, root, filters);
		}
		LinkedList<javax.persistence.criteria.Order> orderList = new LinkedList<javax.persistence.criteria.Order>();
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
		if (orderList != null && orderList.size() > 0) {
			criteriaQuery.orderBy(orderList);
		}
		return criteriaQuery;
	}

	@SuppressWarnings("rawtypes")
	private void setFetchsInCriteria(CriteriaQuery criteria, CriteriaBuilder builder, Root<T> root,
			LinkedList<Fetch> fetchs) {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setFiltrosInCriteria(CriteriaQuery criteria, CriteriaBuilder builder, Root<T> root,
			LinkedList<Filter> filters) {
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

}
