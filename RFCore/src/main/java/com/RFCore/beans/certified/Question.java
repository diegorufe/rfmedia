package com.RFCore.beans.certified;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author diego
 *
 */
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 367691509856492875L;

	private int number;
	private List<Answer> answers = new ArrayList<Answer>();
	private Set<String> sucessAnswers = new LinkedHashSet<String>();
	private String text = "";

	public Question() {

	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<String> getSucessAnswers() {
		return sucessAnswers;
	}

	public void setSucessAnswers(Set<String> sucessAnswers) {
		this.sucessAnswers = sucessAnswers;
	}

}
