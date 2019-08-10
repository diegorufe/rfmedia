package com.RFPM.service.impl;

import org.springframework.stereotype.Service;

import com.RFData.service.impl.BaseServiceImpl;
import com.RFPM.constants.IPMConstantsService;
import com.RFPM.dao.IProyectDao;
import com.RFPM.entities.Proyect;
import com.RFPM.service.IProyectService;

/**
 * 
 * @author diego
 *
 */
@Service(value = IPMConstantsService.SERVICE_PROYECT)
public class ProyectServiceImpl extends BaseServiceImpl<IProyectDao, Proyect, Integer> implements IProyectService {

}
