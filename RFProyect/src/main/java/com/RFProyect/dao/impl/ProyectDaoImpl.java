package com.RFProyect.dao.impl;

import org.springframework.stereotype.Repository;

import com.RFData.dao.jpa.impl.BaseDaoImpl;
import com.RFProyect.dao.IProyectDao;
import com.RFProyect.entities.Proyect;

/**
 * 
 * @author diego
 *
 */
@Repository(IProyectDao.NAME_DAO)
public class ProyectDaoImpl extends BaseDaoImpl<Proyect, Integer> implements IProyectDao {

}
