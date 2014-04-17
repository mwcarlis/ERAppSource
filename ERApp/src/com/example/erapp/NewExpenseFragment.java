package com.example.erapp;

import android.app.Fragment;
import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseImageView;

public class NewExpenseFragment extends Fragment{
	
	private ParseImageView expensePreview;	
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle SavedInstanceState){
		View v = inflater.inflate(R.layout.fragment_new_expense, parent, false);
	
		
		
		
		return parent;
	}
	
	@Override
	public void onResume(){
		super.onResume();
		ParseFile photoFile = ((NewExpenseActivity) getActivity()).getCurrentExpense().getPhotoFile();
		if(photoFile != null){
			expensePreview.setParseFile(photoFile);
			expensePreview.loadInBackground(new GetDataCallback() {
				@Override
				public void done(byte[] data, ParseException e){
					expensePreview.setVisibility(View.VISIBLE);
				}
			});
		}
	}
}
