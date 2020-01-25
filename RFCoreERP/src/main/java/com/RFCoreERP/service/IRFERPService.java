package com.RFCoreERP.service;

import com.RFData.beans.ResponseData;
import com.RFData.entities.RFClient;
import com.RFData.service.IRFClientService;

/**
 *
 * @author diego
 *
 */
public interface IRFERPService {

	public IRFClientService getRfClientService();

	public void setRfClientService(IRFClientService rfClientService);

	public IEnterpriseService getEnterpriseService();

	public void setEnterpriseService(IEnterpriseService enterpriseService);

	/**
	 * Method to create/edit RFClient
	 * 
	 * @param rfClient
	 * @return
	 */
	public default ResponseData<RFClient> createEditRFClient(RFClient rfClient) {
		if (rfClient.getId() != null) {
			// Create rfClient
			this.getRfClientService().save(rfClient, null);
			// Create enterprise
		} else {
			// update rfClient
			this.getRfClientService().update(rfClient, null);
		}
		return new ResponseData<RFClient>(rfClient);
	}

}
