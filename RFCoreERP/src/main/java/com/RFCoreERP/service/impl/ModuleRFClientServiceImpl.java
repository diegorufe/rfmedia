package com.RFCoreERP.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreERP.constants.IConstantsCoreERP;
import com.RFCoreERP.dao.IModuleRFClientDao;
import com.RFCoreERP.entities.ModuleRFClient;
import com.RFCoreERP.entities.ModuleRFClientId;
import com.RFCoreERP.service.IModuleRFClientService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsCoreERP.SERVICE_MODULE_RF_CLIENT)
public class ModuleRFClientServiceImpl extends
		BaseCoreERPServiceImpl<IModuleRFClientDao, ModuleRFClient, ModuleRFClientId> implements IModuleRFClientService {

}
