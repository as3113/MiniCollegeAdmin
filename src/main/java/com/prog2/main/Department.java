package com.prog2.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Department implements Serializable{	
	private String departmentName;
	private String deptID;
	private ArrayList<Teacher> teachers;
	private ArrayList<Staff> staff;

	
	public Department(ArrayList<Teacher> teachers, ArrayList<Staff> staff, String departmentName,
			String deptID) {
		this.teachers = teachers;
		this.staff = staff;
		this.departmentName = departmentName;
		this.deptID = deptID;
	}

	@Override
	public String toString() {
		String output = "\n";
		output += "department name: " + this.departmentName +"\n";
		output += "department id: " + this.deptID +"\n";
		output += "TEACHERS: ";
		for (int i = 0; i < teachers.size(); i++) {
			output += teachers.get(i).toString();
		}
		output += "\nSTAFF: ";
		for (int i = 0; i < staff.size(); i++) {
			output += staff.get(i).toString();
		}
		return output;
	}

	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}

	public ArrayList<Staff> getStaff() {
		return staff;
	}

	public void setStaff(ArrayList<Staff> staff) {
		this.staff = staff;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	
	
	
	
}
