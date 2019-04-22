package com.RFPM.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RFData.dao.IBaseDao;
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
public class ProyectServiceImpl extends BaseServiceImpl<Proyect> implements IProyectService {

	@Autowired
	private IProyectDao dao;

	@Override
	public IProyectDao getDao() {
		return this.dao;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setDao(IBaseDao dao) {
		this.dao = (IProyectDao) dao;
	}
}
