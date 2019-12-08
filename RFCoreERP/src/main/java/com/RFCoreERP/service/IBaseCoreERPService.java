package com.RFCoreERP.service;

import com.RFCoreERP.entities.Enterprise;
import com.RFCoreSecurity.entities.User;
import com.RFData.beans.ResponseData;
import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.entities.RFClient;
import com.RFData.service.IBaseService;

/**
 * 
 * @author diego
 *
 * @param <DAO>
 * @param <T>
 * @param <PK>
 */
public interface IBaseCoreERPService<DAO extends IBaseDao<PK, T>, T extends BaseCoreEntity, PK>
		extends IBaseService<DAO, T, PK> {

	public default ResponseData<T> save(T entidad, RFClient rfClient, Enterprise enterprise, User user) {
		return getDao().save(entidad);
	}

	public default ResponseData<T> update(T entidad, RFClient rfClient, Enterprise enterprise, User user) {
		return getDao().update(entidad);
	}

	public default ResponseData<T> delete(T entidad, RFClient rfClient, Enterprise enterprise, User user) {
		return getDao().delete(entidad);
	}

	public default T loadNew(RFClient rfClient, Enterprise enterprise, User user)
			throws InstantiationException, IllegalAccessException {
		return this.getDao().getGenericClass().newInstance();
	}

}
