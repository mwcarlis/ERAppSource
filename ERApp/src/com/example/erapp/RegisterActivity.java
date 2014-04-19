package com.example.erapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends MainActivity
{
	private Button register;
	private EditText username;
    private EditText password;
    private EditText passwordAgain;
    
    
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		Intent intent = getIntent();
		
		passwordAgain = (EditText)findViewById(R.id.password_again_et);
		username = (EditText)findViewById(R.id.enterUsername);
        password = (EditText)findViewById(R.id.editText2);
        
        register = (Button)findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		}); // end register.setOnClickListener
        
        
        
	}// end onCreate
	
	public void register(View view)
	{
		super.addAccount(username.getText().toString(), password.getText().toString());
		Toast.makeText(getApplicationContext(), "Created the account!",
		          Toast.LENGTH_SHORT).show();
		finish();
	}
}
