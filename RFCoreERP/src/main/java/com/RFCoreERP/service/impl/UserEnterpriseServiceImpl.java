package com.RFCoreERP.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreERP.constants.IConstantsCoreERP;
import com.RFCoreERP.dao.IUserEnterpriseDao;
import com.RFCoreERP.entities.UserEnterprise;
import com.RFCoreERP.entities.UserEnterpriseId;
import com.RFCoreERP.service.IUserEnterpriseService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsCoreERP.SERVICE_USER_ENTERPRISE_CLIENT)
public class UserEnterpriseServiceImpl extends
		BaseCoreERPServiceImpl<IUserEnterpriseDao, UserEnterprise, UserEnterpriseId> implements IUserEnterpriseService {

}
