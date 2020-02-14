package com.RFProyect.dao;

import com.RFData.dao.jpa.IBaseJpaDao;
import com.RFProyect.entities.Proyect;

/**
 * 
 * @author diego
 *
 */
public interface IProyectDao extends IBaseJpaDao<Proyect, Integer> {

	public static final String TABLE_NAME = "proyects";
	public static final String NAME_DAO = "proyectDao";

	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_RFCLIENT = "rfClient";

}
