package com.RFCoreERP.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.RFCoreERP.dao.IUrlSystemDao;
import com.RFData.entities.BaseIdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IUrlSystemDao.TABLE_NAME)
public class UrlSystem extends BaseIdEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;

	@Column(name = IUrlSystemDao.COLUMN_URL, nullable = false, length = 255)
	private String url;
	@Column(name = IUrlSystemDao.COLUMN_DESCRIPTION, nullable = false, length = 255)
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "moduleId", referencedColumnName = "id")
	private Module module;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
