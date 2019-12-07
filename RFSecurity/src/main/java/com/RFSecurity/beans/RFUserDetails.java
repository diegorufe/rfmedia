package com.RFSecurity.beans;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author diego
 *
 */
public class RFUserDetails extends org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5397933083192990112L;
	private Integer userId;

	public RFUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Integer userId) {
		super(username, password, authorities);
		this.userId = userId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
