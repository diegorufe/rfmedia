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

	public abstract <LR extends Set<BaseRole>> LR getRoles();

	public abstract <R extends BaseRole> void setRoles(Set<R> roles);
}
