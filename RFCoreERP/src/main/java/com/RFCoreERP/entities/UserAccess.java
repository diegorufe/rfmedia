package com.RFCoreERP.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.RFCoreERP.dao.IUserAccessDao;
import com.RFCoreSecurity.entities.BaseAuditEntity;
import com.RFData.constants.EnumTypeAccess;
import com.RFData.entities.RFClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IUserAccessDao.TABLE_NAME)
public class UserAccess extends BaseAuditEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private RFClient user;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "urlSystemId", referencedColumnName = "id")
	private UrlSystem urlSystem;

	@Column(name = IUserAccessDao.COLUMN_ENUM_TYPE_ACCESS, columnDefinition = "enum('LIST','READ', 'EDIT', 'DELETE', 'REPORT', 'OTHERS')")
	@Enumerated(EnumType.STRING)
	private EnumTypeAccess enumTypeAccess;

	public RFClient getUser() {
		return user;
	}

	public void setUser(RFClient user) {
		this.user = user;
	}

	public UrlSystem getUrlSystem() {
		return urlSystem;
	}

	public void setUrlSystem(UrlSystem urlSystem) {
		this.urlSystem = urlSystem;
	}

	public EnumTypeAccess getEnumTypeAccess() {
		return enumTypeAccess;
	}

	public void setEnumTypeAccess(EnumTypeAccess enumTypeAccess) {
		this.enumTypeAccess = enumTypeAccess;
	}

}
