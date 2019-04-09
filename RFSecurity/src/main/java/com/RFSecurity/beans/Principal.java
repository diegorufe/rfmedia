package com.RFSecurity.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.RFData.entities.BaseRole;

/**
 * 
 * @author diego
 *
 */
public class Principal extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841347646771955035L;

	private String token;
	private String user;
	private Integer userId;

	public Principal(String username, String password,
			Collection<? extends GrantedAuthority> authorities, Integer userId) {
		super(username, password, authorities);
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
