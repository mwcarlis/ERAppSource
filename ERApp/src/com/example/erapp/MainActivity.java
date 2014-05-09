package com.example.erapp;


import java.util.LinkedList;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

       private EditText  username;
       private EditText  password;

       private Button login;
       private Button register;

       

       @Override
       protected void onCreate(Bundle savedInstanceState) 
       {

          super.onCreate(savedInstanceState);
          
          setContentView(R.layout.activity_main);
 
          username = (EditText)findViewById(R.id.editText1);
          password = (EditText)findViewById(R.id.editText2);
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
          
          
          
          // Set up the submit button click handler
          login.setOnClickListener(new View.OnClickListener() 
          {
            public void onClick(View view) 
            {
              // Validate the log in data
              boolean validationError = false;
              StringBuilder validationErrorMessage =
                  new StringBuilder(getResources().getString(R.string.error_intro));
              if (isEmpty(username)) 
              {
                validationError = true;
                validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
              }
              if (isEmpty(password)) 
              {
                if (validationError) 
                {
                  validationErrorMessage.append(getResources().getString(R.string.error_join));
                }
                validationError = true;
                validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
              }
              validationErrorMessage.append(getResources().getString(R.string.error_end));

              // If there is a validation error, display the error
              if (validationError) 
              {
                Toast.makeText(MainActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
                return;
              }

              // Set up a progress dialog
              final ProgressDialog dlg = new ProgressDialog(MainActivity.this);
              dlg.setTitle("Please wait.");
              dlg.setMessage("Logging in.  Please wait.");
              dlg.show();
              // Call the Parse login method
              ParseUser.logInInBackground(username.getText().toString(), password.getText()
                  .toString(), new LogInCallback() {

                @Override
                public void done(ParseUser user, ParseException e) {
                  dlg.dismiss();
                  if (e != null) 
                  {
                    // Show the error message
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                  } 
                  else 
                  {
                    // Start an intent for the dispatch activity
                    Intent intent = new Intent(MainActivity.this, HistoryListViewActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                  }
                }
              });
            }
          });//end setOnClickListener

       }//end onCreate

       @Override
       public boolean onCreateOptionsMenu(Menu menu) 
       {
          // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.main, menu);
          return true;
       }//end onCreateOptionsMenu
       
       private boolean isEmpty(EditText etText) 
       {
    	    if (etText.getText().toString().trim().length() > 0) 
    	    {
    	      return false;
    	    } else {
    	      return true;
    	    }
    }//end isEmpty
       


      

    }//END MainActivity