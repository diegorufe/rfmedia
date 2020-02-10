package com.RFData.entities;

import java.util.Set;

/**
 * 
 * @author diego
 *
 */
public abstract class BaseRole extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8930068446254877834L;

	public abstract String getName();

	public abstract String getDescription();

	public abstract <U extends BaseUser> Set<U> getUsers();

	public abstract <U extends BaseUser> void setUsers(Set<U> users);
	
	public abstract RFClient getRfClient();
}
