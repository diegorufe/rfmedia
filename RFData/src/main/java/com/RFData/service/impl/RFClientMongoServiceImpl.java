package com.RFData.service.impl;

import org.springframework.stereotype.Service;

import com.RFData.constants.IConstantsData;
import com.RFData.dao.IRFClientMongoDao;
import com.RFData.entities.RFClientMongo;
import com.RFData.service.IRFClientMongoService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsData.SERVICE_RFCLIENT_MONGO)
public class RFClientMongoServiceImpl extends BaseServiceImpl<IRFClientMongoDao, RFClientMongo, String>
		implements IRFClientMongoService {

}
