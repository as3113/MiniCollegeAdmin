package com.prog2.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

public abstract class Person implements PayRoll, Serializable{
	private String firstName;
	private String lastName;
	private int SIN;
	private String email;
	private String address;
	
	public Person(String firstName, String lastName, int sIN, String email, String address){

		this.firstName = firstName;
		this.lastName = lastName;
		SIN = sIN;
		this.email = email;
		this.address = address;
	}
	
	public abstract String defineCategory();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getSIN() {
		return SIN;
	}

	public void setSIN(int sIN) {
		SIN = sIN;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return SIN == other.SIN && Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		String output = "\n";
		output += this.lastName+", "+ this.firstName+"\n";
		output += "    email: " + this.email + "\n    SIN number: " + this.SIN;
		return output;
	}
	

	
}
