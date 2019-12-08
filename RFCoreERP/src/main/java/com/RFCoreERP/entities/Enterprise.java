package com.RFCoreERP.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.RFCoreERP.dao.IEnterpriseDao;
import com.RFCoreSecurity.entities.BaseSecureEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IEnterpriseDao.TABLE_NAME)
public class Enterprise extends BaseSecureEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;

	@Column(name = IEnterpriseDao.COLUMN_CODE, nullable = false, length = 4)
	private String code;
	@Column(name = IEnterpriseDao.COLUMN_DESCRIPTION, nullable = false, length = 255)
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
