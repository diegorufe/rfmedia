package com.RFPM.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.RFData.entities.BaseEntity;
import com.RFPM.dao.IProyectDao;
import com.RFSecurity.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = IProyectDao.TABLE_NAME)
public class Proyect extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 280755173601487773L;

	@Column(name = IProyectDao.COLUMN_CODE, nullable = false, length = 10)
	private String code;
	@Column(name = IProyectDao.COLUMN_NAME, nullable = false, length = 100)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
