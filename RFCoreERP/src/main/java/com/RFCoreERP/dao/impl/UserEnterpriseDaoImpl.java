package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IUserEnterpriseDao;
import com.RFCoreERP.entities.UserEnterprise;
import com.RFCoreERP.entities.UserEnterpriseId;
import com.RFData.dao.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IUserEnterpriseDao.NAME_DAO)
public class UserEnterpriseDaoImpl extends BaseDaoImpl<UserEnterprise, UserEnterpriseId> implements IUserEnterpriseDao {

}
