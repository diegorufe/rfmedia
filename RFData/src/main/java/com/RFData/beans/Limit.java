package com.RFData.beans;

import java.io.Serializable;

/**
 * 
 * @author diego
 *
 */
public class Limit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7106993156590355638L;
	private Integer start;
	private Integer end;

	public Limit() {
	}

	public Limit(Integer start, Integer end) {
		super();
		this.start = start;
		this.end = end;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

}
