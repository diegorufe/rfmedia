package com.RFData.beans;

import java.io.Serializable;
/**
 * 
 * @author diego
 *
 */
public class Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8280060608737670815L;
	
	private int openBrackets;
	private int closedBrackets;
	private String operador;
	private String campo;
	private String condicion;
	private Object valor;
	private String type;
	private String modelAtr;
	private String joinTable;
	private String typeJoin;
	
	public Filter() {
	}
	
	public int getOpenBrackets() {
		return openBrackets;
	}
	public void setOpenBrackets(int openBrackets) {
		this.openBrackets = openBrackets;
	}
	public int getClosedBrackets() {
		return closedBrackets;
	}
	public void setClosedBrackets(int closedBrackets) {
		this.closedBrackets = closedBrackets;
	}
	public String getOperador() {
		return operador;
	}
	public void setOperador(String operador) {
		this.operador = operador;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getCondicion() {
		return condicion;
	}
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModelAtr() {
		return modelAtr;
	}
	public void setModelAtr(String modelAtr) {
		this.modelAtr = modelAtr;
	}
	public String getJoinTable() {
		return joinTable;
	}
	public void setJoinTable(String joinTable) {
		this.joinTable = joinTable;
	}
	public String getTypeJoin() {
		return typeJoin;
	}
	public void setTypeJoin(String typeJoin) {
		this.typeJoin = typeJoin;
	}
	
	

}
