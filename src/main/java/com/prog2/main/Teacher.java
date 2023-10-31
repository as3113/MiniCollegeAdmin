package com.prog2.main;

import java.util.Objects;

public class Teacher extends Person{
	/**
	 * 
	 */

	private String specialty;
	private String degree;
	private boolean isFullTime;
	private int hoursWorked;


	public Teacher(String firstName, String lastName, int sIN, String email, 
			String address, String specialty,
			String degree, boolean isFullTime, int hoursWorked) {
		super(firstName, lastName, sIN, email, address);
		this.specialty = specialty;
		this.degree = degree;
		this.isFullTime = isFullTime;
		this.hoursWorked = hoursWorked;
	}
	@Override
	public double computePayRoll() {
		if(isFullTime) {
			return (32 * degreeRate(getDegree()) * 2 * 0.85) ;
		}
		else {
			return (getHoursWorked() * degreeRate(getDegree()) * 2 * 0.76);
		}
	}
	public int degreeRate(String degree) {
		String str = degree.toLowerCase();
		int degreeRate = 0;
		switch (str) {
		case "phd":
			degreeRate = 112;
			break;
		case "master":
			degreeRate = 82;
			break;
		case "bachelor":
			degreeRate = 42;
			break;
		default:
			break;
		}
		return degreeRate;
	}


	@Override
	public String defineCategory() {
		// TODO Auto-generated method stub
		return "TEACHER";
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public boolean isFullTime() {
		return isFullTime;
	}

	public void setFullTime(boolean isFullTime) {
		this.isFullTime = isFullTime;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		return Objects.equals(degree, other.degree)
				&& isFullTime == other.isFullTime && Objects.equals(specialty, other.specialty);
	}
	@Override
	public String toString() {
		String output = super.toString()+"\n";
		output += "    specialty: " + this.specialty + "\n    degree: "+ this.degree+"\n";
		output += "    full time: " + this.isFullTime + "\n    hours worked: " + this.hoursWorked;
		output += "\n    weekly pay: "+ String.format("$%.2f", this.computePayRoll());
		return output;
	}

	





}
