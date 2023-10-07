package com.evolution.entity;

public class ClassMethodCount {

	 private String className;
	 private int methodCount;

	 public ClassMethodCount(String className, int methodCount) {
		 this.className = className;
		 this.methodCount = methodCount;
	 }
	 public String getClassName() {
		 return className;
	 }
	 public int getMethodCount() {
		 return methodCount;
		 }
}
