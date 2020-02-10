package com.RFData.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFData.dao.IRFClientMongoDao;
import com.RFData.entities.RFClientMongo;

/**
 * 
 * @author diego
 *
 */
@Repository(IRFClientMongoDao.NAME_DAO)
public class RFClientMongoDaoImpl extends BaseDaoImpl<RFClientMongo, String> implements IRFClientMongoDao {

}
