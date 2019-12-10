package com.RFCoreERP.entities;

import java.beans.Transient;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Embeddable;

import com.RFData.entities.BaseCoreEntity;

/**
 * 
 * @author diego
 *
 */
@Embeddable
public class ModuleRFClientId extends BaseCoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 319442066191594840L;

	private Integer moduleId;
	private Integer rfClientId;

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getRfClientId() {
		return rfClientId;
	}

	public void setRfClientId(Integer rfClientId) {
		this.rfClientId = rfClientId;
	}
	
	@Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.moduleId);
        hash = 59 * hash + Objects.hashCode(this.rfClientId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModuleRFClientId other = (ModuleRFClientId) obj;
        if (!Objects.equals(this.moduleId, other.getModuleId())) {
            return false;
        }
        if (!Objects.equals(this.rfClientId, other.getRfClientId())) {
            return false;
        }
        return true;
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
