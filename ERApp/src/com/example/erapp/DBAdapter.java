package com.example.erapp;

import com.parse.ParseObject;

public class DBAdapter {
	static final String KEY_VENDOR="vendor";
	static final String KEY_EXPENSE_DATE="expense_date";
	static final String KEY_SUBMISSION_DATE="submission_date";
	static final String KEY_PAYMENT_TYPE="payment_type";
	static final String KEY_AMOUNT="amount";
	static final String KEY_APPROVED="approved";
	static final String KEY_RECEIPT="receipt";
	static final String KEY_NOTES="notes";
	static final String KEY_FNAME="first_name";
	static final String KEY_LNAME="last_name";
	static final String KEY_EMPLOYEE_ID="employee_id";
	static final String KEY_JOB_TITLE="job_title";
	static final String KEY_EXPENSE_HISTORY="expense_history";
	static final String KEY_RANK="rank";
	
	
	
	boolean editExpense(String ID, Employee u) {
		return false;
	}
	void setNewExpense(Expense a, Employee u){
		
	}
	/*
	ArrayList<Expense>(String ID, Employee u){
		
		
	}
	ArrayList<Employee>(String ID, Employee u){
		
	}
	*/

}
