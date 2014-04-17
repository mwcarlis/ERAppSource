package com.example.erapp;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("Expense")
public class Expense extends ParseObject {
	
	public Expense(){
		//Default constructor required
	}
	
	public String getVendor(){
		return getString("vendor");
	}
	public void setVendor(String vendor){
		put("title", vendor);
	}
	public String getExpenseDate(Date expenseDate) {
		return getString("expense_date");
	}
	public void setExpenseDate(Date expenseDate){
		put("expense_date", expenseDate);
	}
	public String getPaymentType(){
		return getString("payment_type");
	}
	public double getAmount(){
		return getDouble("amount");
	}
	public void setAmount(double purchaseAmount){
		put("amount", purchaseAmount);
	}
	public boolean getApproved(){
		return getBoolean("approved");
	}
	public void setApproved(boolean isApproved){
		put("approved", isApproved);
	}
	public ParseFile getPhotoFile(){
		return getParseFile("receipt_picture");
	}
	public void setPhotoFile(ParseFile file){
		put("receipt_picture", file);
	}
	public String getNotes(){
		return getString("notes");
	}
	public void setNotes(String notesString){
		put("notes", notesString);
	}
	
}// END Expense Class



/*
public class Expense {
	private String vendor;
	private String expenseDate;
	private String submissionDate;
	private String paymentType;
	private double amount;
	private boolean approved;
	private Picture receipt;
	private String notes;


public Expense(String vendInit, String expenDateInit, String subDateInit, String payTypeInit, double amountInit, boolean approvedInit, String notesInit){
	vendor=vendInit; expenseDate=expenDateInit;
	submissionDate=subDateInit; paymentType=payTypeInit;
	amount=amountInit; approved=approvedInit;
	notes=notesInit;
}

//---- getVALUE() Functions ----//
String getVendor(){
	return vendor;
}
String getExpenseDate(){
	return expenseDate;
}
String getSubmissionDate(){
	return submissionDate;
}
String getPaymentType(){
	return paymentType;
}
double getAmount(){
	return amount;
}
boolean getApproved(){
	return approved;
}
Picture getReceipt(){
	return receipt;
}
String getNotes(){
	return notes;
}// END getVALUE() Function //


//---- setVALUE() Functions ----//
void setVendor(String vend){
	vendor=vend;
}
void setExpenseDate(String expenDate){
	expenseDate=expenDate;
}
void setPaymentType(String payType){
	paymentType=payType;
}
void setAmount(double spent){
	amount=spent;
}
void setApproved(boolean aprvd){
	approved=aprvd;
}
void setPicture(Picture receiptPic){
	receipt=receiptPic;
}
void setNotes(String noteStr){
	notes=noteStr;
}// END setVALUE() Functions //

//------------------//
}//END ExpenseCLASS //

*/
