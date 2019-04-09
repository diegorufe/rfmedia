package com.RFData.entities;

import java.util.Set;

/**
 * 
 * @author diego
 *
 */
public abstract class BaseUser extends BaseCoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2496980688032695925L;

	public abstract String getUsername();

	public abstract Set<BaseRole> getRoles();

	public abstract void setRoles(Set<BaseRole> roles);
}
