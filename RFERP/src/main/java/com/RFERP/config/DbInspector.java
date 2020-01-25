package com.RFERP.config;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author diego
 *
 */
public class DbInspector implements StatementInspector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3553497970854179737L;
	private static final Logger LOGGER = LoggerFactory.getLogger(DbInspector.class);

	@Override
	public String inspect(String sql) {
		LOGGER.debug("Executing SQL query: {}", sql);

		return sql;
	}
}
