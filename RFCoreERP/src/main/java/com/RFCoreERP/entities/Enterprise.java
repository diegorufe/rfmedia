package com.RFCoreERP.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.RFCoreERP.dao.IEnterpriseDao;
import com.RFCoreSecurity.entities.BaseAuditEntity;
import com.RFData.entities.RFClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IEnterpriseDao.TABLE_NAME)
public class Enterprise extends BaseAuditEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;

	@Column(name = IEnterpriseDao.COLUMN_CODE, nullable = false, length = 4)
	private String code;
	@Column(name = IEnterpriseDao.COLUMN_DESCRIPTION, nullable = false, length = 255)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "rfClientId", referencedColumnName = "id")
	private RFClient rfClient;

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

	public RFClient getRfClient() {
		return rfClient;
	}

	public void setRfClient(RFClient rfClient) {
		this.rfClient = rfClient;
	}

}
