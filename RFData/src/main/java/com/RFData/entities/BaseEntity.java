package com.RFData.entities;

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
public abstract class BaseEntity extends BaseCoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 118647979101966222L;

	public BaseEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
