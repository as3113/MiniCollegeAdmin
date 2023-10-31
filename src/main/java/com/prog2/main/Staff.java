package com.prog2.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class Staff extends Person{
	private String duty;
	private int workload;

	
public Staff(String firstName, String lastName, int sIN, String email, String address, String duty, int workload){
		super(firstName, lastName, sIN, email, address);
		this.duty = duty;
		this.workload = workload;
	}

// YOU GOTTA ADD THE 40 HR EXCEPTION HANDLER	
	
	@Override
	public double computePayRoll() {
		return workload * 32 * 2 * 0.75;
	}

	@Override
	public String defineCategory() {
		// TODO Auto-generated method stub
		return "STAFF";
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public int getWorkload() {
		return workload;
	}

	public void setWorkload(int workload) {
		this.workload = workload;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(duty, other.duty)
				&& workload == other.workload;
	}

	@Override
	public String toString() {
		String output = super.toString()+"\n";
		output += "    duty: " + this.duty + "\n    workload: " + this.workload;
		output += "\n    weekly pay: " + String.format("$%.2f", this.computePayRoll());
		return output;
	}
}
