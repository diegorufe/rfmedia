package com.RFCoreSecurity.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreSecurity.constants.IConstantsSecurity;
import com.RFCoreSecurity.dao.IUserDao;
import com.RFCoreSecurity.entities.User;
import com.RFCoreSecurity.service.IUserService;
import com.RFData.service.impl.BaseServiceImpl;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsSecurity.SERVICE_USER)
public class UserServiceImpl extends BaseServiceImpl<IUserDao, User, Integer> implements IUserService {

}
