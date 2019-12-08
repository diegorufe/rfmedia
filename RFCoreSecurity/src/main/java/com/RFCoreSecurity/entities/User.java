package com.RFCoreSecurity.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.RFCoreSecurity.dao.IUserDao;
import com.RFData.entities.BaseRole;
import com.RFData.entities.BaseUser;
import com.RFData.entities.RFClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IUserDao.TABLE_NAME)
@NamedQuery(name = IUserDao.NAMED_QUERY_FIND_BY_USERNAME, query = IUserDao.QUERY_FIND_BY_USERNAME)
public class User extends BaseUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8019964445521394095L;

	@Column(name = IUserDao.COLUMN_NICK, nullable = false)
	private String nick;
	@Column(name = IUserDao.COLUMN_PASSWORD, nullable = false)
	@JsonIgnore
	private String password;

//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = IUserDao.COLUMN_USER_ROLES, joinColumns = {
//			@JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	private Set<Role> roles;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "rfClientId", referencedColumnName = "id")
	private RFClient rfClient;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "userUpdateId", referencedColumnName = "id")
	private User userUpdate;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "userCreateId", referencedColumnName = "id")
	private User userCreate;

	@Override
	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Set<Role> getRoles() {
		return this.roles;
	}

	@Override
	public <R extends BaseRole> void setRoles(Set<R> roles) {
		this.roles = (Set<Role>) roles;
	}

	@Override
	public RFClient getRfClient() {
		return rfClient;
	}

	public void setRfClient(RFClient rfClient) {
		this.rfClient = rfClient;
	}

	public User getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(User userUpdate) {
		this.userUpdate = userUpdate;
	}

	public User getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(User userCreate) {
		this.userCreate = userCreate;
	}

}
