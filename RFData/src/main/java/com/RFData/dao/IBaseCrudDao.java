package com.RFData.dao;

import com.RFData.beans.ResponseData;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>
 * @param <T>
 */
public interface IBaseCrudDao<T extends BaseCoreEntity, PK> extends IBaseSimpleDao<T, PK> {

	/**
	 * Method to save a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public ResponseData<T> save(T entidad);

	/**
	 * Method to update a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public ResponseData<T> update(T entidad);

	/**
	 * Method to delete a entity
	 * 
	 * @param entidad
	 */
	public ResponseData<T> delete(T entidad);

	/**
	 * Method to find by id
	 * 
	 * @param id
	 * @return
	 */
	public ResponseData<T> findById(PK id);

}
