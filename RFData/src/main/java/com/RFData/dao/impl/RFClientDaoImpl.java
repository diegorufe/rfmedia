package com.RFData.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFData.dao.IRFClientDao;
import com.RFData.entities.RFClient;

/**
 * 
 * @author diego
 *
 */
@Repository(IRFClientDao.NAME_DAO)
public class RFClientDaoImpl extends BaseDaoImpl<RFClient, Integer> implements IRFClientDao {

}
