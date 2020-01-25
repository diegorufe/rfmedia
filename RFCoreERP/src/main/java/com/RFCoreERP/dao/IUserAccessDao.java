package com.RFCoreERP.dao;

import com.RFCoreERP.entities.UserAccess;
import com.RFData.dao.IBaseDao;

/**
 * 
 * @author diego
 *
 */
public interface IUserAccessDao extends IBaseDao<UserAccess, Integer> {

	public static final String TABLE_NAME = "usersaccess";
	public static final String NAME_DAO = "userAccessDao";

	public static final String COLUMN_USER = "user";
	public static final String COLUMN_URL_SYSTEM = "urlSystem";
	public static final String COLUMN_ENUM_TYPE_ACCESS = "enumTypeAccess";

}
