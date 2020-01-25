package com.RFRestCoreERP.controller.admin;

import com.RFCoreERP.dao.IModuleDao;
import com.RFCoreERP.entities.Module;
import com.RFCoreERP.service.IModuleService;
import com.RFRest.controller.IBaseController;

/**
 * 
 * @author diego
 *
 */
public interface IModuleController extends IBaseController<IModuleService, IModuleDao, Module, Integer> {

}
