package com.RFData.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Order;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>
 * @param <T>
 */
public interface IBaseSearchDao<PK, T extends BaseCoreEntity> extends IBaseSimpleDao<PK, T> {

	/**
	 * Mhetod to find all
	 * 
	 * @return
	 */
	public default List<T> findAll() {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getGenericClass());
		Root<T> root = criteriaQuery.from(getGenericClass());
		CriteriaQuery<T> all = criteriaQuery.select(root);
		TypedQuery<T> allQuery = this.getEntityManager().createQuery(all);
		return allQuery.getResultList();
	}

	/**
	 * Mhetod to count by filters query
	 * 
	 * @param filters
	 * @return
	 */
	public default int count(List<Filter> filters) {
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(getGenericClass());
		criteriaQuery.select(criteriaBuilder.count(root));
		setFiltersInCriteria(criteriaQuery, criteriaBuilder, root, filters);
		Long result = getEntityManager().createQuery(criteriaQuery).getSingleResult();
		return result != null ? result.intValue() : 0;
	}

	/**
	 * Method to find
	 * 
	 * @param fetchs
	 * @param filters
	 * @param orders
	 * @param limits
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public default List<T> find(List<Fetch> fetchs, List<Filter> filters, List<Order> orders, int... limits) {
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
}
