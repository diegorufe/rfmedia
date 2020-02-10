package com.RFData.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.RFData.dao.IRFClientDao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @author diego
 *
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = IRFClientDao.TABLE_NAME)
@AttributeOverride(name = IRFClientDao.ID, column = @Column(name = IRFClientDao.ID))
public class RFClientMongo extends BaseRFClient<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 387058871504889153L;
	private String id;

	@Id
	@GeneratedValue(generator = IRFClientDao.UUID)
	@GenericGenerator(name = IRFClientDao.UUID, strategy = IRFClientDao.UUID_STRATEGY)
	@Override
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
