package com.RFCoreERP.dao;

import com.RFCoreERP.entities.UserEnterprise;
import com.RFCoreERP.entities.UserEnterpriseId;
import com.RFData.dao.IBaseDao;

/**
 * 
 * @author diego
 *
 */
public interface IUserEnterpriseDao extends IBaseDao<UserEnterprise, UserEnterpriseId> {

	public static final String TABLE_NAME = "usersenterprises";
	public static final String NAME_DAO = "userEnterpriseDao";

	public static final String COLUMN_SELECTED = "selected";
	public static final String COLUMN_USER_ID = "userId";
	public static final String COLUMN_ENTERPRISE_ID = "enterpriseId";
	
}
