package com.nanobi.client.constants;

public enum ResultClass {
	FIRST_CLASS_DISCTINCTION(100.0,70.0),FIRST_CLASS(69.99,60.0),SECOND_CLASS(59.99,35.0),FAIL(35.00,0.0);
	
	private double lower;
	private double upper;


	ResultClass(double up, double low) {
		lower = low;
		upper = up;
	}
	public Double getLowerBoundForClass() {
		return this.lower;
	}
	
	
	public Double getUpperBoundForClass(ResultClass c) {
		return this.upper;
	}
	
	
	public static ResultClass getClassForScore(Double score) {
		// TODO Validate
		if(score >= FIRST_CLASS_DISCTINCTION.lower) {
			return FIRST_CLASS_DISCTINCTION;
		} else if (score >= FIRST_CLASS.lower) {
			return FIRST_CLASS;
		} else if (score >= SECOND_CLASS.lower) {
			return SECOND_CLASS;
		} else {
			return FAIL;
		}
	}
}
