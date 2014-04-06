package com.example.erapp;

import java.util.ArrayList;


public class Employee {
	String firstName, lastName;
	int employeeID;
	String job_title;
	ArrayList<Expense> expenseHistory;
	int rank;
	
	public Employee(String fName, String lName, int empID, String title, ArrayList<Expense> expHist, int ran){
		firstName=fName; lastName=lName;
		employeeID=empID; job_title=title;
		expenseHistory=expHist; rank=ran;
	}
	
	
	String getName(){
		return firstName + lastName;
	}
	String getFirstName(){
		return firstName;
	}
	String getLastName(){
		return lastName;
	}
	int getID(){
		return employeeID;
	}
	String getJobTitle(){
		return job_title;
	}
	ArrayList<Expense> getExpenseHistory(){
		return expenseHistory;
	}
	int getRank(){
		return rank;
	}
}//END EmployeeCLASS
