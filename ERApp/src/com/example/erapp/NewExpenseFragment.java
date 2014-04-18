package com.example.erapp;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class NewExpenseFragment extends Fragment{
	
	//private ParseImageView expensePreview;	
	private Button photoButton;
	

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle SavedInstanceState){
		View v = inflater.inflate(R.layout.fragment_new_expense, parent, false);
	
		photoButton = (Button)v.findViewById(R.id.button1);
		photoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				//imm.hideSoftInputFromWindow(windowToken, flags)
				startCamera();
			}
		});
		
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
		//ParseFile photoFile = ((NewExpenseActivity) getActivity()).getCurrentExpense().getPhotoFile();
		//if(photoFile != null){
			//expensePreview.setParseFile(photoFile);
			/*
			expensePreview.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, com.parse.ParseException e){
					expensePreview.setVisibility(View.VISIBLE);
				}
			}); */
		//}
	}// END onResume()
	
} // END NewExpenseFragment
