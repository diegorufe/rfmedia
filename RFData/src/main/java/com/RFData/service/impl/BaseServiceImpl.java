package com.RFData.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.RFData.dao.jpa.IBaseJpaDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.service.IBaseService;

/**
 * 
 * @author diego
 *
 */
public abstract class BaseServiceImpl<DAO extends IBaseJpaDao<T, PK>, T extends BaseCoreEntity, PK>
		implements IBaseService<DAO, T, PK> {

	@Autowired
	private DAO dao;

	@Override
	public DAO getDao() {
		return this.dao;
	}

	@Override
	public void setDao(DAO dao) {
		this.dao = dao;
	}
}
