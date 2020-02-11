package com.RFData.dao.mongo;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.RFData.dao.IBaseDao;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 * @param <PK>
 * @param <T>
 */
public interface IBaseSimpleMongoDao<T extends BaseCoreEntity, PK> extends IBaseDao<T, PK> {

	public MongoTemplate getMongoTemplate();

}
