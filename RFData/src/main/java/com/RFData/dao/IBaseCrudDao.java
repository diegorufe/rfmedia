package com.RFData.dao;

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
public interface IBaseCrudDao<T extends BaseCoreEntity, PK> extends IBaseSimpleDao<T, PK> {

	/**
	 * Method to save a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public default ResponseData<T> save(T entidad) {
		getEntityManager().persist(entidad);
		return new ResponseData<T>(entidad);
	}

	/**
	 * Method to update a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public default ResponseData<T> update(T entidad) {
		return new ResponseData<T>(getEntityManager().merge(entidad));
	}

	/**
	 * Method to delete a entity
	 * 
	 * @param entidad
	 */
	public default ResponseData<T> delete(T entidad) {
		getEntityManager().remove(getEntityManager().contains(entidad) ? entidad : getEntityManager().merge(entidad));
		return new ResponseData<T>(EnumResponseCode.OK.getValue());
	}
	
	/**
	 * Method to find by id
	 * 
	 * @param id
	 * @return
	 */
	public default ResponseData<T> findById(PK id) {
		return new ResponseData<T>(getEntityManager().find(getGenericClass(), id));
	}
	
	

}
