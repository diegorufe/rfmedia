package com.RFCoreERP.dao;

import com.RFCoreERP.entities.ModuleRFClient;
import com.RFCoreERP.entities.ModuleRFClientId;
import com.RFData.dao.IBaseDao;

/**
 * 
 * @author diego
 *
 */
public interface IModuleRFClientDao extends IBaseDao<ModuleRFClientId, ModuleRFClient> {

	public static final String TABLE_NAME = "modulesrfclients";
	public static final String NAME_DAO = "moduleRFClientsDao";

}
