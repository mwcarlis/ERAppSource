package com.example.erapp;


import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

       private EditText  username=null;
       private EditText  password=null;
       private TextView attempts;
       private Button login;
       int counter = 3;
       int index = 0;
       private boolean success = false;
       private Button register;
       
       private LinkedList<Accounts> accountHolder = new LinkedList<Accounts>();

       
       
       @Override
       protected void onCreate(Bundle savedInstanceState) 
       {

          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          
          
          username = (EditText)findViewById(R.id.editText1);
          password = (EditText)findViewById(R.id.editText2);
          attempts = (TextView)findViewById(R.id.textView5);
          attempts.setText(Integer.toString(counter));
          login = (Button)findViewById(R.id.button1);

          
          
          register = (Button)findViewById(R.id.register);
          register.setOnClickListener(new View.OnClickListener() 
          {
  			
  			@Override
  			public void onClick(View v) 
  			{
  				Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
				startActivity(intent);
  			}
  		});

       }
       public void login(View view)
       {
          if((findAccount(username.getText().toString()) && 
          password.getText().toString().equals(accountHolder.get(index).getPassword())) ||( password.getText().toString().equals("") && username.getText().toString().equals("")) )
          {  
	          success = true;
	          Intent intent = new Intent(MainActivity.this, HistoryListViewActivity.class);
			  startActivity(intent);
			  Toast.makeText(getApplicationContext(), "Login Successful!", 
	          Toast.LENGTH_SHORT).show();
          }	
       else
       {
          Toast.makeText(getApplicationContext(), "Wrong Credentials",
          Toast.LENGTH_SHORT).show();
          attempts.setBackgroundColor(Color.RED);	
          counter--;
          attempts.setText(Integer.toString(counter));
          if(counter==0){
             login.setEnabled(false);
          }

       }

    }
       @Override
       public boolean onCreateOptionsMenu(Menu menu) 
       {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.main, menu);
          return true;
       }
       
       @SuppressWarnings("unchecked")
       public void addAccount(String Username, String Password)
       {
    	   accountHolder.add(new Accounts(Username, Password));
    	   //Collections.sort(accountHolder);
       }
       
       public boolean findAccount(String username)
       {
    	   for(int i = 0; i < accountHolder.size(); i++)
    	   {
    		   if(accountHolder.get(i).getUsername().equals(username))
    		   {
    			   index = i;
    			   return true;
    		   }
    	   }
    	   
		return false;
    	   
       }

    }