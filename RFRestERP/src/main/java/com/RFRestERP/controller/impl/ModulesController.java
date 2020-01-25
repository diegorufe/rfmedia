package com.RFRestERP.controller.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFCoreERP.dao.IModuleDao;
import com.RFCoreERP.entities.Module;
import com.RFCoreERP.service.IModuleService;
import com.RFRest.constants.IConstantsRest;
import com.RFRest.controller.impl.BaseControllerImpl;
import com.RFRestERP.constants.IRFRestERPConstants;

/**
 * 
 * @author diego
 *
 */
@CrossOrigin(origins = IConstantsRest.REST_URL_CROSS_ORIGIN, maxAge = IConstantsRest.MAX_AGE_CROSS_ORIGIN)
@RestController
@RequestMapping(IRFRestERPConstants.REST_ADMIN_MODULES)
public class ModulesController extends BaseControllerImpl<IModuleService, IModuleDao, Module, Integer> {

}
