package com.RFRestCoreERP.controller;

import com.RFData.dao.IRFClientDao;
import com.RFData.entities.RFClient;
import com.RFData.service.IRFClientService;
import com.RFRest.controller.IBaseController;

/**
 * 
 * @author diego
 *
 */
public interface IRFClientController extends IBaseController<IRFClientService, IRFClientDao, RFClient, Integer> {

}
