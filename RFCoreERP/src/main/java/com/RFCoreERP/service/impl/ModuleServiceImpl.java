package com.RFCoreERP.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreERP.constants.IConstantsCoreERP;
import com.RFCoreERP.dao.IModuleDao;
import com.RFCoreERP.entities.Module;
import com.RFCoreERP.service.IModuleService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsCoreERP.SERVICE_MODULE)
public class ModuleServiceImpl extends BaseCoreERPServiceImpl<IModuleDao, Module, Integer> implements IModuleService {

}
