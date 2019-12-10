package com.RFCoreERP.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreERP.constants.IConstantsCoreERP;
import com.RFCoreERP.dao.IUrlSystemDao;
import com.RFCoreERP.entities.UrlSystem;
import com.RFCoreERP.service.IUrlSystemService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsCoreERP.SERVICE_URL_SYSTEM)
public class UrlSystemServiceImpl extends BaseCoreERPServiceImpl<IUrlSystemDao, UrlSystem, Integer>
		implements IUrlSystemService {

}
