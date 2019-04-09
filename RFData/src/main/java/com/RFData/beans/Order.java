package com.RFData.beans;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5077075809230860093L;
	private String campo;
	private String tipo;

	public Order() {
	}
	
	

	public Order(String campo, String tipo) {
		super();
		this.campo = campo;
		this.tipo = tipo;
	}



	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
