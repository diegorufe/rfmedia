package com.RFRestCoreERP.controller.admin;

import com.RFCoreERP.service.IBaseCoreERPService;
import com.RFData.dao.jpa.IBaseJpaDao;
import com.RFData.entities.BaseCoreEntity;
import com.RFRest.controller.IBaseController;

/**
 * 
 * @author diego
 *
 */
public interface IBaseCoreERPController<SERVICE extends IBaseCoreERPService<DAO, T, PK>, DAO extends IBaseJpaDao<T, PK>, T extends BaseCoreEntity, PK>
		extends IBaseController<SERVICE, DAO, T, PK> {

}
