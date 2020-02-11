package com.RFData.dao;

import java.lang.reflect.ParameterizedType;

import com.RFData.entities.BaseCoreEntity;
import com.RFData.event.IAuditEventFactory;

/**
 * 
 * @author diego
 *
 */
public interface IBaseSimpleDao<T extends BaseCoreEntity, PK> {
	
	public static final String ID = "id";
	public static final String CODE = "code";
	public static final String DESCRIPTION = "description";
	public static final String UUID = "uuid";

	public static final String SEPARATOR_FIELD_QUERY = ".";

	public static final String UUID_STRATEGY = "uuid2";

	@SuppressWarnings("unchecked")
	public default Class<T> getGenericClass() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) thisType.getActualTypeArguments()[0];
	}

	public IAuditEventFactory getAuditEventFactory();

	public void setAuditEventFactory(IAuditEventFactory auditEventFactory);

}
