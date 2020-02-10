package com.RFData.entities;

import java.util.Set;

import javax.persistence.MappedSuperclass;

/**
 * 
 * @author diego
 *
 */
@MappedSuperclass
public abstract class BaseUser extends BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2496980688032695925L;

	public abstract String getNick();

	public abstract <LR extends Set<BaseRole>> LR getRoles();

	public abstract <R extends BaseRole> void setRoles(Set<R> roles);

	public abstract RFClient getRfClient();
}
