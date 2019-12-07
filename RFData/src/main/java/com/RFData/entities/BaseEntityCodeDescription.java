package com.RFData.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author diego
 *
 */
@MappedSuperclass
public class BaseEntityCodeDescription extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7348047120647072398L;

	@Column(name = "code", nullable = false, length = 10, unique = true)
	private String code;

	@Column(name = "description", nullable = false, length = 200)
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
