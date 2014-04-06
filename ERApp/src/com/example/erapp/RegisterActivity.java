package com.example.erapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends MainActivity
{
	private Button register;
	private EditText username = null;
    private EditText password = null;
    
    
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		Intent intent = getIntent();
		
		username = (EditText)findViewById(R.id.enterUsername);;
        password = (EditText)findViewById(R.id.editText2);
        
        
	}
	
	public void register(View view)
	{
		super.addAccount(username.getText().toString(), password.getText().toString());
		Toast.makeText(getApplicationContext(), "Created the account!",
		          Toast.LENGTH_SHORT).show();
		finish();
	}
}
