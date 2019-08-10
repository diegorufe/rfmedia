package com.RFData.beans;

import java.io.Serializable;
import java.util.List;

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

	private Object value;
	private String operator;
	private String condition;
	private String field;
	private List<Filter> filters;

	public Filter() {
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

}
