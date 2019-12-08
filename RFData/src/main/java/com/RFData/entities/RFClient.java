package com.RFData.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.RFData.dao.IRFClientDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IRFClientDao.TABLE_NAME)
public class RFClient extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;

	@Column(name = IRFClientDao.COLUMN_NAME, nullable = false, unique = true, length = 100)
	private Integer name;

	public Integer getName() {
		return name;
	}

	public void setName(Integer name) {
		this.name = name;
	}

}
