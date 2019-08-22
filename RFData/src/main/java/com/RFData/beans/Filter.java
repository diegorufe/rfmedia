package com.RFData.beans;

import java.io.Serializable;
import java.util.List;

import com.RFData.constants.EnumConditionFilter;
import com.RFData.constants.EnumOperatorFilter;

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

	public Filter(Object value, String operator, String condition, String field, List<Filter> filters) {
		super();
		this.value = value;
		this.operator = operator;
		this.condition = condition;
		this.field = field;
		this.filters = filters;
	}

	public Filter(Object value, String condition, String field, List<Filter> filters) {
		this(value, EnumOperatorFilter.AND.getValue(), condition, field, filters);
	}

	public Filter(Object value, String condition, String field) {
		this(value, EnumOperatorFilter.AND.getValue(), condition, field, null);
	}

	public Filter(Object value, String field, List<Filter> filters) {
		this(value, EnumOperatorFilter.AND.getValue(), EnumConditionFilter.EQUAL.getValue(), field, filters);
	}
	
	public Filter(List<Filter> filters) {
		this(null, null, null, null, filters);
	}

	public Filter(Object value, String field) {
		this(value, EnumOperatorFilter.AND.getValue(), EnumConditionFilter.EQUAL.getValue(), field, null);
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
