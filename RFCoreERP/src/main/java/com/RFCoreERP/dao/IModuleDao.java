package com.RFCoreERP.dao;

import com.RFCoreERP.entities.Module;
import com.RFData.dao.jpa.IBaseJpaDao;

/**
 * 
 * @author diego
 *
 */
public interface IModuleDao extends IBaseJpaDao<Module, Integer> {

	public static final String TABLE_NAME = "modules";
	public static final String NAME_DAO = "moduleDao";

	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";

}
