package com.RFData.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author diego
 *
 */
@MappedSuperclass
public abstract class BaseEntity<PK> extends BaseIdEntity<PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118647979101966222L;

	public BaseEntity() {
	}

	@Column(name = "createdAt", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "updatedAt", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
