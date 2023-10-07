package com.evolution.entity;

public class MethodLineCount {
	 private String methodName;
	    private int lineCount;

	    public MethodLineCount(String methodName, int lineCount) {
	        this.methodName = methodName;
	        this.lineCount = lineCount;
	    }

	    public String getMethodName() {
	        return methodName;
	    }

	    public int getLineCount() {
	        return lineCount;
	    }


}
