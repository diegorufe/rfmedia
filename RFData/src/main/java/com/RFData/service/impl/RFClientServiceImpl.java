package com.RFData.service.impl;

import org.springframework.stereotype.Service;

import com.RFData.constants.IConstantsData;
import com.RFData.dao.jpa.IRFClientDao;
import com.RFData.entities.RFClient;
import com.RFData.service.IRFClientService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsData.SERVICE_RFCLIENT)
public class RFClientServiceImpl extends BaseServiceImpl<IRFClientDao, RFClient, Integer> implements IRFClientService {

}
