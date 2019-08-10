package com.RFSecurity.service;

import com.RFData.service.IBaseService;
import com.RFSecurity.dao.IUserDao;
import com.RFSecurity.entities.User;

/**
 * 
 * @author diego
 *
 */
public interface IUserService extends IBaseService<IUserDao, User, Integer> {

}
