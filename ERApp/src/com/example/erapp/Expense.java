package com.example.erapp;

import java.util.Date;

import android.graphics.Picture;

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
