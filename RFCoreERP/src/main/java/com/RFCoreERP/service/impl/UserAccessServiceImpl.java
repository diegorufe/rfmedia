package com.RFCoreERP.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreERP.constants.IConstantsCoreERP;
import com.RFCoreERP.dao.IUserAccessDao;
import com.RFCoreERP.entities.UserAccess;
import com.RFCoreERP.service.IUserAccessService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsCoreERP.SERVICE_USER_ACESS)
public class UserAccessServiceImpl extends BaseCoreERPServiceImpl<IUserAccessDao, UserAccess, Integer>
		implements IUserAccessService {

}
