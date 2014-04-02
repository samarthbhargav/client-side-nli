package com.nanobi.client.model;

import java.util.HashMap;
import java.util.Map;

public class Student {
	private String name;
	private String usn;
	
	private Map<String, Double> scores;

	public Student() {
		scores  = new HashMap<String, Double>();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsn() {
		return usn;
	}

	public void setUsn(String usn) {
		this.usn = usn;
	}
	
	
	public void addScore(String subject, Double score) {
		scores.put(subject, score);
	}
	
	
	public Double getScore(String subject) {
		return scores.get(subject);
	}
	
	public void clearScores() {
		scores.clear();
	}
	
	public Double getTotal() {
		Double total = 0.0;
		for(String subj : scores.keySet()) {
			total += scores.get(subj);
		}
		return total;
	}
	
	public Double getPercentage(Double maxMarks) {
		return getTotal() / maxMarks;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		String SEP = " ";
		builder.append("Name: " + getName());
		builder.append(SEP + "USN: " + getUsn());
		for(String s : scores.keySet()) {
			builder.append(SEP + s + ": " + scores.get(s));
		}
		builder.append(SEP + "Total: " + getTotal());
		// TODO Add Percentage and Class
		return builder.toString();
	}
}
