package com.RFData.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.RFData.beans.Fetch;
import com.RFData.beans.Filter;
import com.RFData.beans.Order;
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
public interface IBaseDao<PK, T extends BaseCoreEntity> {

	public static final String ID = "id";
	public static final String CODE = "codigo";
	public static final String DESCRIPTION = "descripcion";

	public EntityManager getEntityManager();

	public IAuditEventFactory getAuditEventFactory();

	public void setAuditEventFactory(IAuditEventFactory auditEventFactory);

	public T save(T entidad);

	public T update(T entidad);

	public void delete(T entidad);

	public List<T> findAll();

	public Class<T> getClasegenerica();

	public int count(LinkedList<Filter> filters);

	public List<T> find(LinkedList<Fetch> fetchs, LinkedList<Filter> filters, LinkedList<Order> orders, int... limits);

	public CriteriaQuery<T> createQuery(LinkedList<Fetch> fetchs, LinkedList<Filter> filters, LinkedList<Order> orders);

}
