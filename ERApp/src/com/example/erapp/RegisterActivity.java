package com.example.erapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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
			     // Validate the sign up data
		        boolean validationError = false;
		        StringBuilder validationErrorMessage =
		            new StringBuilder(getResources().getString(R.string.error_intro));
		        if (isEmpty(username)) {
		          validationError = true;
		          validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
		        }
		        if (isEmpty(password)) {
		          if (validationError) {
		            validationErrorMessage.append(getResources().getString(R.string.error_join));
		          }
		          validationError = true;
		          validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
		        }
		        if (!isMatching(password, passwordAgain)) {
		          if (validationError) {
		            validationErrorMessage.append(getResources().getString(R.string.error_join));
		          }
		          validationError = true;
		          validationErrorMessage.append(getResources().getString(
		              R.string.error_mismatched_passwords));
		        }
		        validationErrorMessage.append(getResources().getString(R.string.error_end));

		        // If there is a validation error, display the error
		        if (validationError) {
		          Toast.makeText(RegisterActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
		              .show();
		          return;
		        }

		        // Set up a progress dialog
		        final ProgressDialog dlg = new ProgressDialog(RegisterActivity.this);
		        dlg.setTitle("Please wait.");
		        dlg.setMessage("Signing up.  Please wait.");
		        dlg.show();

		        // Set up a new Parse user
		        ParseUser user = new ParseUser();
		        user.setUsername(username.getText().toString());
		        user.setPassword(password.getText().toString());
		        // Call the Parse signup method
		        user.signUpInBackground(new SignUpCallback() {

		          @Override
		          public void done(ParseException e) {
		            dlg.dismiss();
		            if (e != null) {
		              // Show the error message
		              Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
		            } else {
		              // Start an intent for the dispatch activity
		              Intent intent = new Intent(RegisterActivity.this, DispatchActivity.class);
		              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		              startActivity(intent);
		            }
		          }
		        });
				
			}
		}); // end register.setOnClickListener
         
	}// end onCreate
	
	
	private boolean isEmpty(EditText etText) {
		if (etText.getText().toString().trim().length() > 0) {
		     return false;
		} else {
		     return true;
		 }
	}

	private boolean isMatching(EditText etText1, EditText etText2) {
	    if (etText1.getText().toString().equals(etText2.getText().toString())) {
	      return true;
	    } else {
	      return false;
	    }
	}
	
}// END RegisterActivity
