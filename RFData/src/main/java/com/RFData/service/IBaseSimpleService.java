package com.RFData.service;

import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;
/**
 * 
 * @author diego
 *
 * @param <DAO>
 * @param <T>
 * @param <PK>
 */
public interface IBaseSimpleService<DAO extends IBaseDao<T, PK>, T extends BaseCoreEntity, PK> {
	
	public DAO getDao();
	
	public void setDao(DAO dao);
}
