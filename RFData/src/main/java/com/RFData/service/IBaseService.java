package com.RFData.service;

import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>             is the primary key from database entity
 * @param <BaseCoreEntity> is the base class form database
 *                         {@link #BaseCoreEntity}
 */
public interface IBaseService<DAO extends IBaseDao<T, PK>, T extends BaseCoreEntity, PK>
		extends IBaseSearchService<DAO, T, PK>, IBaseCrudService<DAO, T, PK>, IBaseSimpleService<DAO, T, PK> {

}
