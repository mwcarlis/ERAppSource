package com.example.erapp;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class HistoryListViewActivity extends Activity {
	
	ListView list;
	//private ParseQueryAdapter<Expense> mainAdapter;
	SimpleAdapter adapter;
	

	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if(ParseUser.getCurrentUser() != null)
		{
			getActionBar().setDisplayHomeAsUpEnabled(false);
			setContentView(R.layout.history_list);
			
			Intent intent = getIntent();
			list = (ListView)findViewById(R.id.list);
			ParseUser user = ParseUser.getCurrentUser();
			adapter = new SimpleAdapter(this, user);
			
			list.setAdapter(adapter);
			
		}
		else 
		{
			startActivity(new Intent(this, MainActivity.class));
		}
	}//END onCreate
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_history_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == R.id.menu_title)
		{
			
		}
		else if(item.getItemId() == R.id.action_new)
		{
			newExpense();
		} 
		else if(item.getItemId() == R.id.log_out)
		{
			/*Intent intentDispatch = new Intent(HistoryListViewActivity.this, DispatchActivity.class);
			ParseUser.logOut();
			startActivityForResult(intentDispatch, 0);*/
			new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Log out")
	        .setMessage("Are you sure you want to log out?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick(DialogInterface dialog, int which) 
	        { 
	        	Intent intentMain = new Intent(HistoryListViewActivity.this, MainActivity.class);
				ParseUser.logOut();
				startActivityForResult(intentMain, 0);
				finish();
	        }

	    })
	    .setNegativeButton("No", null)
	    .show();
		}
		return super.onOptionsItemSelected(item);
	}// end onOptionsItemSelected
	
	private void  updateExpenseList()
	{
		
		list.setAdapter(adapter);
		//adapter.notifyDataSetChanged();
		//this.recreate();
	}
	
	private void newExpense()
	{
		Intent i = new Intent(HistoryListViewActivity.this, NewExpenseActivity.class);
		startActivityForResult(i, 0);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if(resultCode == Activity.RESULT_OK)
		{
			updateExpenseList();
		}
	}
	
	/*@Override
	public void onBackPressed() 
	{
        super.onBackPressed();
        this.finish();
        Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}*/
	@Override
	public void onBackPressed() 
	{
	    new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Log out")
	        .setMessage("Are you sure you want to log out?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	    {
	        @Override
	        public void onClick(DialogInterface dialog, int which) 
	        { 
	        	Intent intentMain = new Intent(HistoryListViewActivity.this, MainActivity.class);
				ParseUser.logOut();
				startActivityForResult(intentMain, 0);
				finish();
	        }

	    })
	    .setNegativeButton("No", null)
	    .show();
	}
	
	
}//END HistoryListViewActivity
