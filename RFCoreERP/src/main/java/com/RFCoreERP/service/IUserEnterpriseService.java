package com.RFCoreERP.service;

import java.util.ArrayList;
import java.util.List;

import com.RFCore.utils.collection.UtilsCollection;
import com.RFCoreERP.dao.IUserEnterpriseDao;
import com.RFCoreERP.entities.UserEnterprise;
import com.RFCoreERP.entities.UserEnterpriseId;
import com.RFData.beans.Filter;
import com.RFData.beans.Limit;

/**
 * 
 * @author diego
 *
 */
public interface IUserEnterpriseService
		extends IBaseCoreERPService<IUserEnterpriseDao, UserEnterprise, UserEnterpriseId> {

	/**
	 * Method to find selectd enterprise per users
	 * 
	 * @param userId is the user id for search enterprise
	 * @return
	 */
	public default UserEnterprise findSelectedEnterprise(int userId) {
		UserEnterprise userEnterprise = null;
		List<Filter> listFilters = new ArrayList<Filter>();
		listFilters.add(new Filter(true, IUserEnterpriseDao.COLUMN_SELECTED));
		listFilters.add(new Filter(userId,
				IUserEnterpriseDao.ID + IUserEnterpriseDao.SEPARATOR_FIELD_QUERY + IUserEnterpriseDao.COLUMN_USER_ID));

		List<UserEnterprise> listUserEnterprises = this.find(null, listFilters, null, new Limit(0, 1), null);

		if (UtilsCollection.isListNotNull(listUserEnterprises)) {
			userEnterprise = listUserEnterprises.get(0);
		}

		return userEnterprise;
	}
}
