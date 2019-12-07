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

	private Integer id;
	private String nick;
	private Object permisions;

	public Principal() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

}
