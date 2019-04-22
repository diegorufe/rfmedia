package com.RFPM.dao;

import com.RFData.dao.IBaseDao;
import com.RFPM.entities.Proyect;

/**
 * 
 * @author diego
 *
 */
public interface IProyectDao extends IBaseDao<Integer, Proyect> {

	public static final String TABLE_NAME = "proyects";
	public static final String NAME_DAO = "proyectDao";

	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_USER = "user";
}
