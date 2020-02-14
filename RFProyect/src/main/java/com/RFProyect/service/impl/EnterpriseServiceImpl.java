package com.RFProyect.service.impl;

import org.springframework.stereotype.Service;

import com.RFCoreERP.service.impl.BaseCoreERPServiceImpl;
import com.RFProyect.constants.IConstantsProyect;
import com.RFProyect.dao.IProyectDao;
import com.RFProyect.entities.Proyect;
import com.RFProyect.service.IProyectService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IConstantsProyect.SERVICE_PROYECT)
public class EnterpriseServiceImpl extends BaseCoreERPServiceImpl<IProyectDao, Proyect, Integer>
		implements IProyectService {

}
