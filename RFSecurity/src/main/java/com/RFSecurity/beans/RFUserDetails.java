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
	/**
	 * This use object because object id sometimes is String
	 */
	private Object userId;

	public RFUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities,
			Object userId) {
		super(username, password, authorities);
		this.userId = userId;
	}

	public Object getUserId() {
		return userId;
	}

	public void setUserId(Object userId) {
		this.userId = userId;
	}

}
