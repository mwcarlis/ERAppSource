package com.example.erapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ViewExpenseActivity extends Activity
{
	String Vendor;
	String Amount;
	String Date;
	String Notes;
	String Status;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.view_expense);
	        Intent i = getIntent();
	        Vendor = i.getStringExtra("Vendor");
	        Amount = i.getStringExtra("ammount");
	        Date = i.getStringExtra("purchaseDate");
	        Status = i.getStringExtra("pending");
	        Notes = i.getStringExtra("note");
	        
	        TextView txtVendor = (TextView) findViewById(R.id.vendorView); 
	        TextView txtAmount = (TextView) findViewById(R.id.amountView);
	        TextView txtDate = (TextView) findViewById(R.id.dateView);
	        TextView txtStatus = (TextView) findViewById(R.id.statusView);
	        TextView txtNote = (TextView) findViewById(R.id.noteView);
	        
	        txtVendor.setText("  " + Vendor);
	        txtAmount.setText("  $" + Amount);
	        txtDate.setText("  " + Date);
	        txtStatus.setText(Status);
	        txtNote.setText(" " + Notes);

	    }
}
