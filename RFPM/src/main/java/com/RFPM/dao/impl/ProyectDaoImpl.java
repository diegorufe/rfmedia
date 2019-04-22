package com.RFPM.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFData.dao.impl.BaseDaoImpl;
import com.RFPM.dao.IProyectDao;
import com.RFPM.entities.Proyect;

/**
 * 
 * @author diego
 *
 */
@Repository(IProyectDao.NAME_DAO)
public class ProyectDaoImpl extends BaseDaoImpl<Integer, Proyect> implements IProyectDao {

}
