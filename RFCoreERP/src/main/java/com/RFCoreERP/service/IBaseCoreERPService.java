package com.RFCoreERP.service;

import java.util.Map;

import com.RFData.beans.ResponseData;
import com.RFData.dao.jpa.IBaseJpaDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.service.IBaseService;

/**
 * 
 * @author diego
 *
 * @param <DAO>
 * @param <T>
 * @param <PK>
 */
public interface IBaseCoreERPService<DAO extends IBaseJpaDao<T, PK>, T extends BaseCoreEntity, PK>
		extends IBaseService<DAO, T, PK> {

	public default ResponseData<T> save(T entidad, int rfClientId, int enterpriseId, int userId,
			Map<String, Object> params) {
		return this.save(entidad, params);
	}

	public default ResponseData<T> update(T entidad, int rfClientId, int enterpriseId, int userId,
			Map<String, Object> params) {
		return this.update(entidad, params);
	}

	public default ResponseData<T> delete(T entidad, int rfClientId, int enterpriseId, int userId,
			Map<String, Object> params) {
		return this.delete(entidad, params);
	}

	public default T loadNew(int rfClientId, int enterpriseId, int userId, Map<String, Object> mapPropertiesFixLoad,
			Map<String, Object> params) throws InstantiationException, IllegalAccessException {
		return this.loadNew(mapPropertiesFixLoad, params);
	}

}
