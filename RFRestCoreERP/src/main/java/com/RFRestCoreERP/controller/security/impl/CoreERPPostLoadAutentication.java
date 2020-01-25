package com.RFRestCoreERP.controller.security.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RFCoreERP.entities.UserEnterprise;
import com.RFCoreERP.service.IEnterpriseService;
import com.RFCoreERP.service.IUserEnterpriseService;
import com.RFCoreSecurity.constants.IConstantsSecurity;
import com.RFCoreSecurity.service.IPostAutenticationService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsSecurity.SERVICE_POST_LOAD_AUTENTICATION)
public class CoreERPPostLoadAutentication implements IPostAutenticationService {

	@Autowired
	private IUserEnterpriseService userEnterpriseService;

	@Autowired
	private IEnterpriseService enterpriseService;

	@Override
	public Map<String, Object> postAutenticate(Map<String, Object> params) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		UserEnterprise userEnterprise = this.userEnterpriseService.findSelectedEnterprise(2);
		return params;
	}
}
