package com.RFCoreERP.entities;

import java.beans.Transient;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 */
@Embeddable
public class UserEnterpriseId extends BaseCoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319442066191594840L;

	private Integer userId;
	private Integer enterpriseId;
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + Objects.hashCode(this.userId);
		hash = 59 * hash + Objects.hashCode(this.enterpriseId);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final UserEnterpriseId other = (UserEnterpriseId) obj;
		if (!Objects.equals(this.userId, other.getUserId())) {
			return false;
		}
		if (!Objects.equals(this.enterpriseId, other.getEnterpriseId())) {
			return false;
		}
		return true;
	}

	@Transient
	@Override
	public Date getCreatedAt() {
		return null;
	}

	@Transient
	@Override
	public Date getUpdatedAt() {
		return null;
	}

}
