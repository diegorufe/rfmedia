package com.RFData.dao.jpa;

import com.RFData.entities.RFClient;

/**
 * 
 * @author diego
 *
 */
public interface IRFClientDao extends IBaseJpaDao<RFClient, Integer> {

	public static final String TABLE_NAME = "rfclients";
	public static final String NAME_DAO = "rfclientsDao";

	public static final String COLUMN_NAME = "name";

}
