package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IEnterpriseDao;
import com.RFCoreERP.entities.Enterprise;
import com.RFData.dao.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IEnterpriseDao.NAME_DAO)
public class EnterpriseDaoImpl extends BaseDaoImpl<Integer, Enterprise> implements IEnterpriseDao {

}
