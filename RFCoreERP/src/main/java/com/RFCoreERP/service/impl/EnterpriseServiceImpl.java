package com.RFCoreERP.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreERP.constants.IConstantsCoreERP;
import com.RFCoreERP.dao.IEnterpriseDao;
import com.RFCoreERP.entities.Enterprise;
import com.RFCoreERP.service.IEnterpriseService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsCoreERP.SERVICE_ENTERPRISE)
public class EnterpriseServiceImpl extends BaseCoreERPServiceImpl<IEnterpriseDao, Enterprise, Integer>
		implements IEnterpriseService {

}
