package com.RFCoreERP.service.impl;

import com.RFCoreERP.service.IBaseCoreERPService;
import com.RFData.dao.jpa.IBaseJpaDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFData.service.impl.BaseServiceImpl;

/**
 * 
 * @author diego
 *
 */
public abstract class BaseCoreERPServiceImpl<DAO extends IBaseJpaDao<T, PK>, T extends BaseCoreEntity, PK>
		extends BaseServiceImpl<DAO, T, PK> implements IBaseCoreERPService<DAO, T, PK> {

}
