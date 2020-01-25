package com.RFData.dao;

import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>             is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *                         {@link #BaseCoreEntity}
 */
public interface IBaseDao<T extends BaseCoreEntity, PK>
		extends IBaseSearchDao<T, PK>, IBaseCrudDao<T, PK>, IBaseSimpleDao<T, PK> {

}
