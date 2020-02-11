package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IEnterpriseDao;
import com.RFCoreERP.entities.Enterprise;
import com.RFData.dao.jpa.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IEnterpriseDao.NAME_DAO)
public class EnterpriseDaoImpl extends BaseDaoImpl<Enterprise, Integer> implements IEnterpriseDao {

}
