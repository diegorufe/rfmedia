package com.RFCoreSecurity.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFCoreSecurity.dao.IUserDao;
import com.RFCoreSecurity.entities.User;
import com.RFData.dao.jpa.impl.BaseDaoImpl;

/**
 * 
 * @author diego
 *
 */
@Repository(IUserDao.NAME_DAO)
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements IUserDao {

}
