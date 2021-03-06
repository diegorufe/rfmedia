package com.RFRestCoreERP.controller.admin.impl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFData.dao.jpa.IRFClientDao;
import com.RFData.entities.RFClient;
import com.RFData.service.IRFClientService;
import com.RFRest.constants.IConstantsRest;
import com.RFRest.controller.impl.BaseControllerImpl;
import com.RFRestCoreERP.comstants.IRFRestERPConstants;

/**
 * 
 * @author diego
 *
 */
@CrossOrigin(origins = IConstantsRest.REST_URL_CROSS_ORIGIN, maxAge = IConstantsRest.MAX_AGE_CROSS_ORIGIN)
@RestController
@RequestMapping(IRFRestERPConstants.REST_ADMIN_RF_CLIENTS)
public class RFClientControllerImpl extends BaseControllerImpl<IRFClientService, IRFClientDao, RFClient, Integer> {

}
