package com.prog2.main;

public class Dean extends Teacher{
	
	public Dean(String firstName, String lastName, int sIN, String email, String address, String specialty,
			String degree, boolean isFullTime, int hoursWorked) {
		super(firstName, lastName, sIN, email, address, specialty, degree, isFullTime, hoursWorked);
		;
	}

	@Override
	public String toString() {
		return "\nDEAN"+super.toString();
	}
	
}
