package com.RFData.dao;

import javax.persistence.EntityManager;

/**
 * 
 * @author diego
 *
 * @param <PK> is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *        {@link #BaseCoreEntity}
 */
public interface IBaseDao<PK, BaseCoreEntity> {
	
	public EntityManager getEntityManager();
	
}
