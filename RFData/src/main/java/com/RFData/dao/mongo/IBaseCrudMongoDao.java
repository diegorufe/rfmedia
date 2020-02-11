package com.RFData.dao.mongo;

import com.RFData.beans.ResponseData;
import com.RFData.constants.EnumResponseCode;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>
 * @param <T>
 */
public interface IBaseCrudMongoDao<T extends BaseCoreEntity, PK> extends IBaseSimpleMongoDao<T, PK> {

	/**
	 * Method to save a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public default ResponseData<T> save(T entidad) {
		return new ResponseData<T>(getMongoTemplate().save(entidad));
	}

	/**
	 * Method to update a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public default ResponseData<T> update(T entidad) {
		return new ResponseData<T>(getMongoTemplate().save(entidad));
	}

	/**
	 * Method to delete a entity
	 * 
	 * @param entidad
	 */
	public default ResponseData<T> delete(T entidad) {
		getMongoTemplate().remove(entidad);
		return new ResponseData<T>(EnumResponseCode.OK.getValue());
	}

	/**
	 * Method to find by id
	 * 
	 * @param id
	 * @return
	 */
	public default ResponseData<T> findById(PK id) {
		return new ResponseData<T>(getMongoTemplate().findById(id, getGenericClass()));
	}

}
