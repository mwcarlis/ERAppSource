package com.example.erapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

public class DispatchActivity extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		if(ParseUser.getCurrentUser() != null)
		{
			startActivity(new Intent(this, HistoryListViewActivity.class));
		}
		else 
		{
			startActivity(new Intent(this, MainActivity.class));
		}

	}// end onCreate()

}// END DispatchActivity 
