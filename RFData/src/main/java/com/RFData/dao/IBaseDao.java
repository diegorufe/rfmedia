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
public interface IBaseDao<PK, T extends BaseCoreEntity>
		extends IBaseSearchDao<PK, T>, IBaseCrudDao<PK, T>, IBaseSimpleDao<PK, T> {

}
