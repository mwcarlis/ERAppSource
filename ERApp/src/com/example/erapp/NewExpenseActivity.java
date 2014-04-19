package com.example.erapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class NewExpenseActivity extends Activity {
	private Expense expense;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		expense = new Expense();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		
		// begin with main data entry view,
		// NewExpenseFragment
		setContentView(R.layout.activity_new_expense);
		//android.app.FragmentManager instead of support library?
		FragmentManager manager = getFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
		
		if(fragment == null){
			fragment = new NewExpenseFragment();
			manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}	
	} // end onCreate
	
	public Expense getCurrentExpense(){
		return expense;
	}

	

} // END NewExpenseActivity
