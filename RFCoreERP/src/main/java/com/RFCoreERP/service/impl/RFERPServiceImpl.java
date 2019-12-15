package com.RFCoreERP.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.RFCoreERP.service.IEnterpriseService;
import com.RFCoreERP.service.IRFERPService;
import com.RFData.service.IRFClientService;

/**
 * 
 * @author diego
 *
 */
public class RFERPServiceImpl implements IRFERPService {

	@Autowired
	private IRFClientService rfClientService;

	@Autowired
	private IEnterpriseService enterpriseService;

	@Override
	public IRFClientService getRfClientService() {
		return rfClientService;
	}

	@Override
	public void setRfClientService(IRFClientService rfClientService) {
		this.rfClientService = rfClientService;
	}

	@Override
	public IEnterpriseService getEnterpriseService() {
		return enterpriseService;
	}

	@Override
	public void setEnterpriseService(IEnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

}
