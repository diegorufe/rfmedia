package com.RFCoreERP.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreERP.dao.IUserAccessDao;
import com.RFCoreERP.entities.UserAccess;
import com.RFData.dao.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IUserAccessDao.NAME_DAO)
public class UserAccessDaoImpl extends BaseDaoImpl<Integer, UserAccess> implements IUserAccessDao {

}
