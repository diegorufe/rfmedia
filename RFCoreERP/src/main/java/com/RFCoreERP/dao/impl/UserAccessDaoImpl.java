package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IUserAccessDao;
import com.RFCoreERP.entities.UserAccess;
import com.RFData.dao.jpa.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IUserAccessDao.NAME_DAO)
public class UserAccessDaoImpl extends BaseDaoImpl<UserAccess, Integer> implements IUserAccessDao {

}
