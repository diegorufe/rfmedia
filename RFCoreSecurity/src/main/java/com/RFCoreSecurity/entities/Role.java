package com.RFCoreSecurity.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.RFCoreSecurity.dao.IUserDao;
import com.RFData.entities.BaseRole;
import com.RFData.entities.BaseUser;
import com.RFData.entities.RFClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "roles")
public class Role extends BaseRole {

	/**
	 * 
	 */
	private static final long serialVersionUID = -239761483324885038L;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = IUserDao.COLUMN_USER_ROLES, joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
	private Set<User> users;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "rfClientId", referencedColumnName = "id")
	private RFClient rfClient;

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

	@SuppressWarnings("unchecked")
	public Set<User> getUsers() {
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <U extends BaseUser> void setUsers(Set<U> users) {
		this.users = (Set<User>) users;
	}

	@Override
	public RFClient getRfClient() {
		return rfClient;
	}

	public void setRfClient(RFClient rfClient) {
		this.rfClient = rfClient;
	}
}
