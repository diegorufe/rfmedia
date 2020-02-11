package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IModuleDao;
import com.RFCoreERP.entities.Module;
import com.RFData.dao.jpa.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IModuleDao.NAME_DAO)
public class ModuleDaoImpl extends BaseDaoImpl<Module, Integer> implements IModuleDao {

}
