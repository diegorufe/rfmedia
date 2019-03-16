package com.RFData.dao.impl;
/**
 * 
 * @author diego
 *
 */

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.RFData.dao.IBaseDao;

/**
 * 
 * @author diego
 *
 * @param <PK> is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *        {@link #BaseCoreEntity}
 */
public class BaseDaoImpl<PK, BaseCoreEntity> implements IBaseDao<PK, BaseCoreEntity> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

}
