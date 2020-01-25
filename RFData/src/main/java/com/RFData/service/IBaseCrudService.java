package com.RFData.service;

import java.util.Map;

import com.RFCore.utils.collection.UtilsCollection;
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
public interface IBaseCrudService<DAO extends IBaseDao<T, PK>, T extends BaseCoreEntity, PK>
		extends IBaseSimpleService<DAO, T, PK> {

	public default ResponseData<T> save(T entidad, Map<String, Object> params) {
		return getDao().save(entidad);
	}

	public default ResponseData<T> update(T entidad, Map<String, Object> params) {
		return getDao().update(entidad);
	}

	public default ResponseData<T> delete(T entidad, Map<String, Object> params) {
		return getDao().delete(entidad);
	}

	/**
	 * Method to load new instance
	 * 
	 * @param mapPropertiesFixLoad properties to fix in entity
	 * @paran params
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public default T loadNew(Map<String, Object> mapPropertiesFixLoad, Map<String, Object> params)
			throws InstantiationException, IllegalAccessException {
		T instace = this.getDao().getGenericClass().newInstance();
		if (UtilsCollection.isMapNotNull(mapPropertiesFixLoad)) {
			for (String key : mapPropertiesFixLoad.keySet()) {
				// TODO set prperties
			}
		}
		return instace;
	}
}
