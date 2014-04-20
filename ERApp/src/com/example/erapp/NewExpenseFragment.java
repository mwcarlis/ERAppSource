package com.example.erapp;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NewExpenseFragment extends Fragment{
	
	//private ParseImageView expensePreview;	
	private EditText vendorET;
	private EditText purchaseDateET;
	private EditText amountET;
	private EditText notesET;
	
	private Button photoButton;
	private Button saveButton;
	private Button cancelButton;
	private ParseImageView expensePreview;
	

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//setHasOptionsMenu(true);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle SavedInstanceState){
		View v = inflater.inflate(R.layout.fragment_new_expense, parent, false);
		
		vendorET = (EditText)v.findViewById(R.id.vendor_et);
		purchaseDateET = (EditText)v.findViewById(R.id.purchase_date_et);
		amountET = (EditText)v.findViewById(R.id.amount_et);
		notesET = (EditText)v.findViewById(R.id.notes_et);
		final ParseUser user = ParseUser.getCurrentUser();
	
		photoButton = (Button)v.findViewById(R.id.take_picture_button);
		photoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				//imm.hideSoftInputFromWindow(expenseName, flags)
				startCamera();
			}
		}); // end photoButton.setOnClickListener
		
		cancelButton = (Button)v.findViewById(R.id.cancel_button);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().setResult(Activity.RESULT_CANCELED);
				getActivity().finish();
			}
		}); // end canvelButton.setOnClickListener
		
		saveButton = (Button)v.findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Expense expense = ((NewExpenseActivity) getActivity()).getCurrentExpense();
				expense.setAuthor(ParseUser.getCurrentUser());
				
				expense.setVendor(vendorET.getText().toString());
				expense.setExpenseDate(purchaseDateET.getText().toString());
				expense.setNotes(notesET.getText().toString());
				expense.setAmount(Double.parseDouble(amountET.getText().toString()));
				expense.setAuthor(user);
				expense.setApproved("Pending");
				
				expense.saveInBackground(new SaveCallback() {

					@Override
					public void done(ParseException e) {
						if (e == null) {
							getActivity().setResult(Activity.RESULT_OK);
							getActivity().finish();
						} else {
							Toast.makeText(
									getActivity().getApplicationContext(),
									"Error saving: " + e.getMessage(),
									Toast.LENGTH_SHORT).show();
						}
					}// end done
				});
				
			}// end onClick
		}); // end saveButton.setOnClickListener
		
		expensePreview = (ParseImageView) v.findViewById(R.id.expense_preview_image);
		expensePreview.setVisibility(View.INVISIBLE);
		
		return v;
	} // end onCreateView
	
	
	public void startCamera(){
		Fragment cameraFragment = new CameraFragment();
		FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragmentContainer, cameraFragment);
		transaction.addToBackStack("NewExpenseFragment");
		transaction.commit();
	}
	
	
	
	@Override
	public void onResume(){
		super.onResume();
		ParseFile photoFile = ((NewExpenseActivity) getActivity()).getCurrentExpense().getPhotoFile();
		
		if(photoFile != null){
			expensePreview.setParseFile(photoFile);
			expensePreview.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, com.parse.ParseException e){
					expensePreview.setVisibility(View.VISIBLE);
				}
			}); 
		}  
	}// END onResume()
	
	
} // END NewExpenseFragment
