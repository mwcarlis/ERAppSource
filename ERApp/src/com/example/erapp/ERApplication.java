package com.example.erapp;

import android.app.Application;
import android.content.SharedPreferences;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;


// Androids "Application" class allows us to create singleton objects to exist in their own lifecycle
//			 	behind Activities.  Use Application to access/organize/control application global data/info.
public class ERApplication extends Application {
	
	private static SharedPreferences preferences;

	
	@Override
	public void onCreate(){
		super.onCreate();
	
		ParseObject.registerSubclass(Expense.class);
		Parse.initialize(this, "LZreCmKLdoTzWHFIBYBtGqiU2vqhioxNDPsqlmFH", "NOZLD2078ZCYverfSOgHhp4GA3AfkvyTcrCB0zbY");
		
		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		//optionally enable public read access.
		defaultACL.setPublicReadAccess(false);
		ParseACL.setDefaultACL(defaultACL, true);
	}
	
}/// END ERApplication

























/*
static final String KEY_EXPENSE="expense";
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

//Date today = new Date(2014, 4, 5);
//ArrayList<Expense> myArray = new ArrayList<Expense>();
//Expense myExpense = new Expense("Starbucks", "today", "today", "Cash", 15.25, false, "no notes");
//Employee Myself = new Employee("Matt", "Carlis", 310, "Manager", myArray ,10);
//ParseObject ExpenseObj = new ParseObject(Myself.getFirstName() + Myself.getLastName());


//ExpenseObj.put(KEY_FNAME, Myself.getFirstName());


//ExpenseObj.put(KEY_LNAME, Myself.getLastName());
//ExpenseObj.put(KEY_EMPLOYEE_ID, Myself.getID());
//ExpenseObj.put(KEY_JOB_TITLE, Myself.getJobTitle());
//ExpenseObj.put(KEY_RANK, Myself.getRank());



//ExpenseObj.put(KEY_EXPENSE_DATE, myExpense.getExpenseDate());
//ExpenseObj.put(KEY_SUBMISSION_DATE, myExpense.getSubmissionDate());

//ExpenseObj.put(KEY_VENDOR, myExpense.getVendor());
//ExpenseObj.put(KEY_PAYMENT_TYPE, myExpense.getPaymentType());
//ExpenseObj.put(KEY_AMOUNT, myExpense.getAmount());
//ExpenseObj.put(KEY_APPROVED, myExpense.getApproved());
//ExpenseObj.put(KEY_NOTES, myExpense.getNotes());


//ExpenseObj.saveInBackground();

*/