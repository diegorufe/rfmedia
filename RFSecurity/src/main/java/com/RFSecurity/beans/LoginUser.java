package com.RFSecurity.beans;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class LoginUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8255673499539925194L;
	private String username;
	private String password;
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
	
	
}
