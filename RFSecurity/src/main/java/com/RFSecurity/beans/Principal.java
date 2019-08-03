package com.RFSecurity.beans;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class Principal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841347646771955035L;

	private String token;
	private String user;
	private Integer userId;

	public Principal() {
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
