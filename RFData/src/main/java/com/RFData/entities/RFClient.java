package com.RFData.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.RFData.dao.jpa.IRFClientDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IRFClientDao.TABLE_NAME)
public class RFClient extends BaseRFClient<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;

	

}
