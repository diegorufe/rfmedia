package com.RFCore.beans;

public class PairValues<A,B> {
	
	private A valueA;
	private B valueB;
	
	public PairValues() {
	}
	
	public PairValues(A valueA, B valueB) {
		super();
		this.valueA = valueA;
		this.valueB = valueB;
	}



	public A getValueA() {
		return valueA;
	}

	public void setValueA(A valueA) {
		this.valueA = valueA;
	}

	public B getValueB() {
		return valueB;
	}

	public void setValueB(B valueB) {
		this.valueB = valueB;
	}
	
	
}
