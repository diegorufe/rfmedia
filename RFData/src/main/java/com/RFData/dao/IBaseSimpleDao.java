package com.RFData.dao;

import java.lang.reflect.ParameterizedType;

import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 */
public interface IBaseSimpleDao<T extends BaseCoreEntity, PK> {

	@SuppressWarnings("unchecked")
	public default Class<T> getGenericClass() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[0];
	}

}
