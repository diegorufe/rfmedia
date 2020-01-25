package com.RFCoreERP.dao;

import com.RFCoreERP.entities.Enterprise;
import com.RFData.dao.IBaseDao;

/**
 * 
 * @author diego
 *
 */
public interface IEnterpriseDao extends IBaseDao<Enterprise, Integer> {

	public static final String TABLE_NAME = "enterprises";
	public static final String NAME_DAO = "enterpriseDao";

	public static final String COLUMN_CODE = "code";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_RFCLIENT = "rfClient";

}
