package com.RFSecurity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.RFData.entities.BaseEntity;

/**
 * 
 * @author diego
 *
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5582389483618761214L;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
