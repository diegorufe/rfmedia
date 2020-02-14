package com.RFRestCoreERP.controller.admin.impl;

import com.RFCoreERP.service.IBaseCoreERPService;
import com.RFData.dao.jpa.IBaseJpaDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFRest.controller.impl.BaseControllerImpl;
import com.RFRestCoreERP.controller.admin.IBaseCoreERPController;

/**
 * 
 * @author diego
 *
 * @param <SERVICE>
 * @param <DAO>
 * @param <T>
 * @param <PK>
 */
public class BaseCoreERPController<SERVICE extends IBaseCoreERPService<DAO, T, PK>, DAO extends IBaseJpaDao<T, PK>, T extends BaseCoreEntity, PK>
		extends BaseControllerImpl<SERVICE, DAO, T, PK> implements IBaseCoreERPController<SERVICE, DAO, T, PK> {

}
