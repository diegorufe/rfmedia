package com.RFData.service;

import com.RFData.beans.ResponseData;
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
public interface IBaseCrudService<DAO extends IBaseDao<PK, T>, T extends BaseCoreEntity, PK>
		extends IBaseSimpleService<DAO, T, PK> {

	public default ResponseData<T> save(T entidad) {
		return getDao().save(entidad);
	}

	public default ResponseData<T> update(T entidad) {
		return getDao().update(entidad);
	}

	public default ResponseData<T> delete(T entidad) {
		return getDao().delete(entidad);
	}

	public default T loadNew() throws InstantiationException, IllegalAccessException {
		return this.getDao().getGenericClass().newInstance();
	}
}
