package com.RFData.dao;

import com.RFData.entities.RFClientMongo;

/**
 * 
 * @author diego
 *
 */
public interface IRFClientMongoDao extends IBaseDao<RFClientMongo, String> {

	public static final String TABLE_NAME = "rfclients";
	public static final String NAME_DAO = "rfclientsMongoDao";

	public static final String COLUMN_NAME = "name";

}
