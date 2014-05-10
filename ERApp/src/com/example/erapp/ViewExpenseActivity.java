package com.example.erapp;

import java.io.ByteArrayOutputStream;

import com.parse.Parse;
import com.parse.ParseFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class ViewExpenseActivity extends Activity
{
	String Vendor;
	String Amount;
	String Date;
	String Notes;
	String Status;
	String ObjectId;
	byte[] imageURL;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) 
	 {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.view_expense);
	        Intent i = getIntent();
	        Vendor = i.getStringExtra("Vendor");
	        Amount = i.getStringExtra("amount");
	        Date = i.getStringExtra("purchaseDate");
	        Status = i.getStringExtra("pending");
	        Notes = i.getStringExtra("note");
	        ObjectId = i.getStringExtra("ObjectId");
	        imageURL = i.getByteArrayExtra("image");
	        
	        TextView txtVendor = (TextView) findViewById(R.id.vendorView); 
	        TextView txtAmount = (TextView) findViewById(R.id.amountView);
	        TextView txtDate = (TextView) findViewById(R.id.dateView);
	        TextView txtStatus = (TextView) findViewById(R.id.statusView);
	        TextView txtNote = (TextView) findViewById(R.id.noteView);
	        if(imageURL != null)
	        {
		        ImageView imgReceipt = (ImageView) findViewById(R.id.recieptImage);
		        
		        Bitmap picture = BitmapFactory.decodeByteArray(imageURL, 0, imageURL.length);
		        ByteArrayOutputStream stream = new ByteArrayOutputStream();
		        picture.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		        imgReceipt.setImageBitmap(picture);
	        }

	        
	        
	        
	        txtVendor.setText("  " + Vendor);
	        txtAmount.setText("  $" + Amount);
	        txtDate.setText("  " + Date);
	        txtStatus.setText(Status);
	        txtNote.setText(" " + Notes);
	        

	    }
	 
	 
}
