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

}
