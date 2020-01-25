package com.RFCoreERP.entities;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.RFCoreERP.dao.IUserEnterpriseDao;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 */
@Entity
@Table(name = IUserEnterpriseDao.TABLE_NAME)
public class UserEnterprise extends BaseCoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319442066191594840L;

	@EmbeddedId
	private UserEnterpriseId id;

	@Column(name = IUserEnterpriseDao.COLUMN_SELECTED, nullable = false)
	private boolean selected;

	public UserEnterpriseId getId() {
		return id;
	}

	public void setId(UserEnterpriseId id) {
		this.id = id;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
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
