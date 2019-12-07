package com.RFData.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author diego
 *
 */
public abstract class BaseCoreEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4502873208030814565L;
	
	public abstract Date getCreatedAt();
	
	public abstract Date getUpdatedAt();

}
