package com.RFData.dao.jpa.impl;

/**
 * 
 * @author diego
 *
 */

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.RFData.dao.jpa.IBaseJpaDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.event.IAuditEventFactory;

/**
 * 
 * @author diego
 *
 * @param <PK>             is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *                         {@link #BaseCoreEntity}
 */
public abstract class BaseDaoImpl<T extends BaseCoreEntity, PK> implements IBaseJpaDao<T, PK> {

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

}
