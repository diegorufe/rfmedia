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

	private Object id;
	private String nick;
	private Object permisions;
	private String token;

	public Principal() {
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Object getPermisions() {
		return permisions;
	}

	public void setPermisions(Object permisions) {
		this.permisions = permisions;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

}
