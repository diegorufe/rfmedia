package com.RFData.dao.jpa;

import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>             is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *                         {@link #BaseCoreEntity}
 */
public interface IBaseJpaDao<T extends BaseCoreEntity, PK>
		extends IBaseSearchJpaDao<T, PK>, IBaseCrudJpaDao<T, PK>, IBaseSimpleJpaDao<T, PK> {

}
