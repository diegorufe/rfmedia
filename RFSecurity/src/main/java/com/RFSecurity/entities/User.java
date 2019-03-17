package com.RFSecurity.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.RFData.entities.BaseEntity;
import com.RFSecurity.dao.IUserDao;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author diego
 *
 */
@Entity
@Table(name = IUserDao.TABLE_NAME)
@NamedQueries({ @NamedQuery(name = IUserDao.NAMED_QUERY_FIND_BY_USERNAME, query = IUserDao.QUERY_FIND_BY_USERNAME) })
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019964445521394095L;

	@Column(name = IUserDao.COLUMN_USERNAME, nullable = false)
	private String username;
	@Column(name = IUserDao.COLUMN_PASSWORD, nullable = false)
	@JsonIgnore
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = IUserDao.COLUMN_USER_ROLES, joinColumns = {
			@JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}
