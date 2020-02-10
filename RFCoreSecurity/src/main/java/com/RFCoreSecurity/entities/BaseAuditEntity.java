package com.RFCoreSecurity.entities;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.RFData.entities.BaseEntity;

/**
 * 
 * @author diego
 *
 */
@MappedSuperclass
public abstract class BaseAuditEntity<PK> extends BaseEntity<PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118647979101966222L;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "userUpdateId", referencedColumnName = "id")
	private User userUpdate;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "userCreateId", referencedColumnName = "id")
	private User userCreate;

	public BaseAuditEntity() {
	}

	public User getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(User userUpdate) {
		this.userUpdate = userUpdate;
	}

	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

}
