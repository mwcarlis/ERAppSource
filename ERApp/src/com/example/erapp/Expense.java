package com.example.erapp;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("Expense")
public class Expense extends ParseObject {
	
	public Expense(){
		//Default constructor required
	}
	public ParseUser getAuthor(){
		return getParseUser("author");
	}
	public void setAuthor(ParseUser user){
		put("author", user);
	}
	
	public String getVendor(){
		return getString("vendor");
	}
	public void setVendor(String vendName){
		put("vendor", vendName);
	}
	public String getExpenseDate() {
		return getString("expense_date");
	}
	public void setExpenseDate(String expenseDate){
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
	public String getApproved(){
		return getString("approved");
	}
	public void setApproved(String isApproved){
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
