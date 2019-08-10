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
public interface IBaseCrudService<DAO extends IBaseDao<PK, T>, T extends BaseCoreEntity, PK>
		extends IBaseSimpleService<DAO, T, PK> {

	public default T save(T entidad) {
		getDao().save(entidad);
		return entidad;
	}

	public default T update(T entidad) {
		getDao().update(entidad);
		return entidad;
	}

	public default void delete(T entidad) {
		getDao().delete(entidad);
	}

	public default T loadNew() throws InstantiationException, IllegalAccessException {
		return this.getDao().getGenericClass().newInstance();
	}
}
