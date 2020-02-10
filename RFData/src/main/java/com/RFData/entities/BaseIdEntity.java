package com.RFData.entities;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author diego
 *
 */
@MappedSuperclass
public abstract class BaseIdEntity<PK> extends BaseCoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118647979101966222L;

	public BaseIdEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private PK id;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	@Transient
	@Override
	public Date getCreatedAt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transient
	@Override
	public Date getUpdatedAt() {
		// TODO Auto-generated method stub
		return null;
	}

}
