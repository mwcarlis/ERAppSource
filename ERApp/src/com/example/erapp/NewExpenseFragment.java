package com.example.erapp;


import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.nfc.FormatException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NewExpenseFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
	
	
	//private ParseImageView expensePreview;	
	private EditText vendorET;
	private EditText purchaseDateET;
	private EditText amountET;
	private EditText notesET;
	
	private EditText buttonTView;
	
	private Button photoButton;
	private Button saveButton;
	private Button cancelButton;
    private Spinner payment_type;
	private ParseImageView expensePreview;
	
	Calendar myCalendar = Calendar.getInstance();
	int startYear = myCalendar.get(Calendar.YEAR);
	int startMonth = myCalendar.get(Calendar.MONTH);
	int startDay = myCalendar.get(Calendar.DAY_OF_MONTH);
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//setHasOptionsMenu(true);
		
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle SavedInstanceState){
		View v = inflater.inflate(R.layout.fragment_new_expense1, parent, false);
		
		vendorET = (EditText)v.findViewById(R.id.vendor_et);
		purchaseDateET = (EditText)v.findViewById(R.id.purchase_date_et);
		amountET = (EditText)v.findViewById(R.id.amount_et);
		notesET = (EditText)v.findViewById(R.id.notes_et);
		final ParseUser user = ParseUser.getCurrentUser();
		payment_type = (Spinner)v.findViewById(R.id.payment_type_spinner);
		buttonTView = (EditText)v.findViewById(R.id.buttonTextView);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
		        R.array.payment_type_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		payment_type.setAdapter(adapter);
		
		
		buttonTView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment picker = new DatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
				
			}
		}); 
	

	
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
				
				boolean valid = true;
				double value=0;
				Expense expense = ((NewExpenseActivity) getActivity()).getCurrentExpense();

				
				//expense.setAmount(Double.parseDouble(amountET.getText().toString()));
				try{
					value = Double.parseDouble(amountET.getText().toString());
				}catch(NumberFormatException exc){
					
				}
			
				if(valid = true){
					expense.setAuthor(ParseUser.getCurrentUser());
					
					expense.setVendor(vendorET.getText().toString());
					expense.setExpenseDate(buttonTView.getText().toString());
					expense.setNotes(notesET.getText().toString());
					expense.setAuthor(user);
					expense.setApproved("Pending");
					expense.setAmount(value);
				
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
			}else {
				
				
			}
				
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

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	}
	public void updateDateTextView(String dateStr){
		buttonTView.setText(dateStr);
	}
	
} // END NewExpenseFragment
