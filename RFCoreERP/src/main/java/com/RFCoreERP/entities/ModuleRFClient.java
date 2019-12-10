package com.RFCoreERP.entities;

import java.beans.Transient;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.RFCoreERP.dao.IModuleRFClientDao;
import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 */
@Entity
@Table(name = IModuleRFClientDao.TABLE_NAME)
public class ModuleRFClient extends BaseCoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319442066191594840L;

	@EmbeddedId
	private ModuleRFClientId id;

	public ModuleRFClientId getId() {
		return id;
	}

	public void setId(ModuleRFClientId id) {
		this.id = id;
	}

	@Transient
	@Override
	public Date getCreatedAt() {
		return null;
	}

	@Transient
	@Override
	public Date getUpdatedAt() {
		return null;
	}

}
