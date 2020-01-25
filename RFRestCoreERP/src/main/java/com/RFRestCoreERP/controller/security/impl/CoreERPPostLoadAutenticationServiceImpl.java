package com.RFRestCoreERP.controller.security.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RFCoreERP.entities.Enterprise;
import com.RFCoreERP.entities.UserEnterprise;
import com.RFCoreERP.service.IEnterpriseService;
import com.RFCoreERP.service.IUserEnterpriseService;
import com.RFCoreSecurity.constants.EnumParamOptionsSecurity;
import com.RFCoreSecurity.constants.IConstantsSecurity;
import com.RFCoreSecurity.service.IPostAutenticationService;
import com.RFData.beans.ResponseData;
import com.RFRestCoreERP.comstants.EnumParamOptionsERP;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsSecurity.SERVICE_POST_LOAD_AUTENTICATION)
public class CoreERPPostLoadAutenticationServiceImpl implements IPostAutenticationService {

	@Autowired
	private IUserEnterpriseService userEnterpriseService;

	@Autowired
	private IEnterpriseService enterpriseService;

	@Override
	public Map<String, Object> postAutenticate(Map<String, Object> params) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		if (params.containsKey(EnumParamOptionsSecurity.PARAM_SECURITY_USER_ID.getValue())
				&& params.get(EnumParamOptionsSecurity.PARAM_SECURITY_USER_ID.getValue()) != null) {
			UserEnterprise userEnterprise = this.userEnterpriseService.findSelectedEnterprise(
					(int) params.get(EnumParamOptionsSecurity.PARAM_SECURITY_USER_ID.getValue()));
			// If has selected enterprise. Use then
			if (userEnterprise != null) {
				ResponseData<Enterprise> responseData = this.enterpriseService
						.findById(userEnterprise.getId().getEnterpriseId(), null);
				if (responseData != null && responseData.getEntity() != null) {
					params.put(EnumParamOptionsERP.PARAM_ERP_SELECTED_ENTERPRISE.getValue(), responseData.getEntity());
				}
			}
		}
		return params;
	}
}
