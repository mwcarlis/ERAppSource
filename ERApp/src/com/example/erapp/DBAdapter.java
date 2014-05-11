package com.example.erapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.ParseException;

import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class DBAdapter {
	
	private ArrayList<localExpense> expenseList;

	public DBAdapter(){
		try {
			query();
		} catch (com.parse.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} // end DBAdapt Constructor

	public ArrayList<localExpense> getExpenseList(){
		return expenseList;
	}// endgetExpenseList()
	
	public void flushExpenses(){
		expenseList.clear();
		expenseList = null;
	} // end flushExpense()
	
	public void refreshExpense(){
		
		if(expenseList == null){
			try {
				query();
			} catch (com.parse.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			flushExpenses();
			try {
				query();
			} catch (com.parse.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// end refreshExpense()
	
	void query() throws com.parse.ParseException{
		expenseList = new ArrayList<localExpense>();
		try
		{
			ParseUser user = ParseUser.getCurrentUser();
			List<ParseObject> ob;
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Expense");
			query.orderByDescending("createdAt");
			query.whereEqualTo("user", user);
			ob = query.find();
			for(ParseObject currentExpense : ob)
			{
				ParseFile receiptImage = (ParseFile)( (Expense)currentExpense).getPhotoFile();
				ParseFile payTypeImage = (ParseFile)((Expense)currentExpense).getPaymentType();
				localExpense current = new localExpense();
				current.setUserId(user);
				current.setDate(((Expense) currentExpense).getExpenseDate());
				current.setVendor(((Expense) currentExpense).getVendor());
				current.setAmount(((Expense) currentExpense).getAmount());
				current.setNotes(((Expense) currentExpense).getNotes());
				current.setStatus(((Expense) currentExpense).getApproved());
				current.setObjectId(currentExpense.getObjectId());
				
				if(payTypeImage!= null)
				{
					current.setPaymentType(payTypeImage);
				}
				
				if(receiptImage != null)
				{
					current.setPhotoFile(receiptImage);
				}
				
				expenseList.add(current);
			}// end for(ParseObject currentExpense : ob)
		
		}catch(ParseException e)
		{
			e.printStackTrace();
		}
		
	}// end query()
}// end DBAdapt
