package com.RFCoreERP.dao;

import com.RFCoreERP.entities.UrlSystem;
import com.RFData.dao.jpa.IBaseJpaDao;

/**
 * 
 * @author diego
 *
 */
public interface IUrlSystemDao extends IBaseJpaDao<UrlSystem, Integer> {

	public static final String TABLE_NAME = "urlssystem";
	public static final String NAME_DAO = "urlSystemDao";

	public static final String COLUMN_URL = "url";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_MODULE = "module";

}
