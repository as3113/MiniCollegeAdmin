package com.prog2.main;

public class DepartmentNotFoundException extends Exception{
	public DepartmentNotFoundException(String message) {
		super(message);
	}
	public DepartmentNotFoundException() {
		super();
	}
}
// why throw this custom exception if the generic works?
