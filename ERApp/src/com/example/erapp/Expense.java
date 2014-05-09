package com.example.erapp;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;


@ParseClassName("Expense")
public class Expense extends ParseObject 
{
	
	public Expense()
	{
		//Default constructor required
	}
	public ParseUser getAuthor()
	{
		return getParseUser("user");
	}
	public void setAuthor(ParseUser user)
	{
		put("user", user);
	}
	
	public String getVendor()
	{
		return getString("vendor");
	}
	
	public void setVendor(String vendName)
	{
		put("vendor", vendName);
	}
	
	public String getExpenseDate() 
	{
		return getString("expense_date");
	}
	
	public void setExpenseDate(String expenseDate)
	{
		put("expense_date", expenseDate);
	}
	
	public String getPaymentType()
	{
		return getString("payment_type");
	}
	
	public double getAmount()
	{
		return getDouble("amount");
	}
	
	public void setAmount(double purchaseAmount)
	{
		put("amount", purchaseAmount);
	}
	
	public String getApproved()
	{
		return getString("approved");
	}
	
	public void setApproved(String isApproved)
	{
		put("approved", isApproved);
	}
	
	public ParseFile getPhotoFile()
	{
		return getParseFile("receipt_picture");
	}
	
	public void setPhotoFile(ParseFile file)
	{
		put("receipt_picture", file);
	}
	
	public String getNotes()
	{
		return getString("notes");
	}
	
	public void setNotes(String notesString)
	{
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

*/
