package com.RFSecurity.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFData.dao.impl.BaseDaoImpl;
import com.RFSecurity.dao.IUserDao;
import com.RFSecurity.entities.User;

/**
 * 
 * @author diego
 *
 */
@Repository(IUserDao.NAME_DAO)
public class UserDaoImpl extends BaseDaoImpl<Integer, User> implements IUserDao {

}
