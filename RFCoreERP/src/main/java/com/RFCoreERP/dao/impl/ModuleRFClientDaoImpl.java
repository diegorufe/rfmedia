package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IModuleRFClientDao;
import com.RFCoreERP.entities.ModuleRFClient;
import com.RFCoreERP.entities.ModuleRFClientId;
import com.RFData.dao.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IModuleRFClientDao.NAME_DAO)
public class ModuleRFClientDaoImpl extends BaseDaoImpl<ModuleRFClient, ModuleRFClientId> implements IModuleRFClientDao {

}
