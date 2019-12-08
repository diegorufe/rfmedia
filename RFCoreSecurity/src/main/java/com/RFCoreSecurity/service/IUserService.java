package com.RFCoreSecurity.service;

import com.RFCoreSecurity.dao.IUserDao;
import com.RFCoreSecurity.entities.User;
import com.RFData.service.IBaseService;

/**
 * 
 * @author diego
 *
 */
public interface IUserService extends IBaseService<IUserDao, User, Integer> {

}
