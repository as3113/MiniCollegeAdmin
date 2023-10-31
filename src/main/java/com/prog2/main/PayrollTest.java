package com.prog2.main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;



class PayrollTest {
	boolean isFullTime;
	String degree;
	int hoursWorked;
	static int testNumber = 1;
	
	@BeforeEach
	void setup() {
		System.out.println("test " + testNumber + " beginning.");
	}
	@Test
	// full time phd
	void computePayrolltest1() {
		 degree = "phd";
		 isFullTime = true;
		 
		 double actual = new Teacher(null, null, 0, null, null, null, degree, isFullTime, 0).computePayRoll();
		 double expected = (32 * 112 * 2) * 0.85;
		 if(actual != expected) {
			 System.out.println("test "+ testNumber + " failed");
		 }
		 
		 assertEquals(expected, actual);
	}
	
	@Test
	// full time masters
	void computePayrolltest2() {
		 degree = "master";
		 isFullTime = true;
		 
		 double actual = new Teacher(null, null, 0, null, null, null, degree, isFullTime, 0).computePayRoll();
		 double expected = (32 * 82 * 2) * 0.85;
		 if(actual != expected) {
			 System.out.println("test "+ testNumber + " failed");
		 }
		 assertEquals(expected, actual);
	}
	@Test
	// full time bachelor
		void computePayrolltest3() {
			 degree = "bachelor";
			 isFullTime = true;
			 
			 double actual = new Teacher(null, null, 0, null, null, null, degree, isFullTime, 0).computePayRoll();
			 double expected = (32 * 42 * 2) * 0.85;
			 if(actual != expected) {
				 System.out.println("test "+ testNumber + " failed");
			 }
			 assertEquals(expected, actual);

		}
	@Test
		// part time bachelor
		void computePayrolltest4() {
			 degree = "bachelor";
			 hoursWorked = 20;
			 isFullTime = false;
			 
			 double actual = new Teacher(null, null, 0, null, null, null, degree, isFullTime, hoursWorked).computePayRoll();
			 double expected = (hoursWorked * 42 * 2) * 0.76;
			 if(actual != expected) {
				 System.out.println("test "+ testNumber + " failed");
			 }
			 assertEquals(expected, actual);
		}
	@Test
		// part time master
		void computePayrolltest5() {
			 degree = "master";
			 hoursWorked = 20;
			 isFullTime = false;
			 
			 double actual = new Teacher(null, null, 0, null, null, null, degree, isFullTime, hoursWorked).computePayRoll();
			 double expected = (hoursWorked * 82 * 2) * 0.76;
			 if(actual != expected) {
				 System.out.println("test "+ testNumber + " failed");
			 }
			 assertEquals(expected, actual);
		}
	
	@Test
		// part time phd
		void computePayrolltest6() {
			 degree = "phd";
			 hoursWorked = 20;
			 isFullTime = false;
			 
			 double actual = new Teacher(null, null, 0, null, null, null, degree, isFullTime, hoursWorked).computePayRoll();
			 double expected = (hoursWorked * 112 * 2) * 0.76;
			 if(actual != expected) {
				 System.out.println("test "+ testNumber + " failed");
			 }
			 assertEquals(expected, actual);
		}
	@Test
		// staff payroll
		void computePayrolltest7() {
			 hoursWorked = 20;
			 // String firstName, String lastName, int sIN, String email, String address, String duty, int workload)
			 double actual = new Staff(null, null, 0, null, null, null, hoursWorked).computePayRoll();
			 double expected = (hoursWorked * 32 * 2) * 0.75;
			 if(actual != expected) {
				 System.out.println("test "+ testNumber + " failed");
			 }
			 assertEquals(expected, actual);
		}
	@AfterEach
		void teardownEach() {
		
		System.out.println("test " + testNumber++ + " completed.");
		System.out.println();
	}

}
