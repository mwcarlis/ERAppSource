package com.example.erapp;

import java.util.Date;

import android.graphics.Picture;

public class Expense {
String vendor;
Date expenseDate;
Date submissionDate;
String paymentType;
double amount;
boolean approved;
Picture receipt;
String notes;


public Expense(String vendInit, Date expenDateInit, Date subDateInit, String payTypeInit, double amountInit, boolean approvedInit, Picture receiptInit, String notesInit){
	vendor=vendInit; expenseDate=expenDateInit;
	submissionDate=subDateInit; paymentType=payTypeInit;
	amount=amountInit; approved=approvedInit;
	receipt=receiptInit; notes=notesInit;
}

//---- getVALUE() Functions ----//
String getVendor(){
	return vendor;
}
Date getExpenseDate(){
	return expenseDate;
}
Date getSubmissionDate(){
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
void setExpenseDate(Date expenDate){
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
