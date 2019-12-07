package com.RFData.dao;

import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>
 * @param <T>
 */
public interface IBaseCrudDao<PK, T extends BaseCoreEntity> extends IBaseSimpleDao<PK, T> {

	/**
	 * Method to save a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public default T save(T entidad) {
		getEntityManager().persist(entidad);
		return entidad;
	}

	/**
	 * Method to update a entity
	 * 
	 * @param entidad
	 * @return
	 */
	public default T update(T entidad) {
		return getEntityManager().merge(entidad);
	}

	/**
	 * Method to delete a entity
	 * 
	 * @param entidad
	 */
	public default void delete(T entidad) {
		getEntityManager().remove(getEntityManager().contains(entidad) ? entidad : getEntityManager().merge(entidad));
	}

}
