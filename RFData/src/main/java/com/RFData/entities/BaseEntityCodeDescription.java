package com.RFData.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author diego
 *
 */
@MappedSuperclass
public class BaseEntityCodeDescription extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7348047120647072398L;

	@Column(name = "codigo", nullable = false, length = 10, unique = true)
	private String codigo;

	@Column(name = "descripcion", nullable = false, length = 200)
	private String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
