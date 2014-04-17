package com.example.erapp;

import java.util.ArrayList;
import java.util.Date;

import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenuActivity extends Activity
{
	private Button addItem;
	private Button history;
	private Button settings;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		Intent intent = getIntent();
		Parse.initialize(this, "LZreCmKLdoTzWHFIBYBtGqiU2vqhioxNDPsqlmFH", "NOZLD2078ZCYverfSOgHhp4GA3AfkvyTcrCB0zbY");
		
		//DBAdapter myDBAdapt = new DBAdapter();

		
		//myDBAdapt.setNewExpense(myExpense, Myself);
		
		addItem = (Button)findViewById(R.id.addExpense);
		history = (Button)findViewById(R.id.expenseHistory);
		settings = (Button)findViewById(R.id.accountSettings);	
		
		history.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainMenuActivity.this, HistoryListViewActivity.class);
				startActivity(intent);
			}
		});
		
		addItem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(MainMenuActivity.this, NewExpenseActivity.class);
				startActivity(intent);
			}
		});
		
		settings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(getApplicationContext(), "Coming soon",
				          Toast.LENGTH_SHORT).show();
			}
		});
	}
        

	
	public void addItem(View view)
	{
		Toast.makeText(getApplicationContext(), "Coming soon",
		          Toast.LENGTH_SHORT).show();
	}
	
	public void history(View view)
	{
		Intent intent = new Intent(MainMenuActivity.this, HistoryListViewActivity.class);
		startActivity(intent);
	}
	
	public void settings(View view)
	{
		Toast.makeText(getApplicationContext(), "Coming soon",
		          Toast.LENGTH_SHORT).show();
	}

}
