package com.RFCoreERP.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.RFCoreERP.dao.IModuleDao;
import com.RFData.entities.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IModuleDao.TABLE_NAME)
public class Module extends BaseIdEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;

	@Column(name = IModuleDao.COLUMN_NAME, nullable = false, length = 100)
	private String name;
	@Column(name = IModuleDao.COLUMN_DESCRIPTION, nullable = false, length = 255)
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
