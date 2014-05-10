package com.example.erapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;

public class localExpense 
{
	private String vendor;
	private double amount;
	private String date;
	private String notes;
	private String status;
	private String objectId;
	private ParseUser userId;
	private ParseFile photoFile;
	private Bitmap paymentTypeImage;
	
	public String getVendor()
	{
		return vendor;
	}
	
	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getNotes()
	{
		return notes;
	}
	
	public void setNotes(String notes)
	{
		this.notes = notes;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getObjectId()
	{
		return objectId;
	}
	
	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}
	
	public ParseUser getUserId()
	{
		return userId;
	}
	
	public void setUserId(ParseUser userId)
	{
		this.userId = userId;
	}
	
	public ParseFile getPhotoFile()
	{
		return photoFile;
	}
	
	public void setPhotoFile(ParseFile photoFile)
	{
		this.photoFile = photoFile;
	}
	public Bitmap getPaymentType()
	{
		return paymentTypeImage;
	}
	public void setPaymentType(ParseFile payType) throws ParseException
	{
		byte[] bites = payType.getData();
		Bitmap bm = BitmapFactory.decodeByteArray(bites, 0, bites.length);
		this.paymentTypeImage = bm;
	}
	
	
	
}
