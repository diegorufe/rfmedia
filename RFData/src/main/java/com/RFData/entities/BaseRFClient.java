package com.RFData.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.RFData.dao.IRFClientDao;

/**
 * 
 * @author diego
 *
 */
@MappedSuperclass
public class BaseRFClient<PK> extends BaseEntity<PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6625038806115934598L;

	@Column(name = IRFClientDao.COLUMN_NAME, nullable = false, unique = true, length = 100)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
