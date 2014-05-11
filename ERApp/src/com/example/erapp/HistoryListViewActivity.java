package com.example.erapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseUser;

public class HistoryListViewActivity extends Activity {
	
	ListView list;
	SimpleAdapter adapter;
	ParseUser user;
	
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
			
			user = ParseUser.getCurrentUser();
			adapter = new SimpleAdapter(this, user, new ArrayList<localExpense>());//POSSIBLE HAZARD IF NULL

			list.setAdapter(adapter);	
			(new AsyncListLoader()).execute("");
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
		if(item.getItemId() == R.id.menu_title){
			
		} else if(item.getItemId() == R.id.action_new){
			newExpense();
		} else if(item.getItemId() == R.id.action_export){
			
			ParseUser whoAmI = ParseUser.getCurrentUser();
			if(whoAmI.getBoolean("emailVerified") == true){
				
				DBAdapter adaptDB = new DBAdapter();
				String csv_Format = formatCSV(adaptDB.getExpenseList());
			
				File file   = null;
				File root   = Environment.getExternalStorageDirectory();
				Uri u1 = fileURI(root, csv_Format);
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822");
				i.putExtra(Intent.EXTRA_EMAIL,  whoAmI.getEmail() );
				i.putExtra(Intent.EXTRA_SUBJECT, ".csv formatted expense history");
				i.putExtra(Intent.EXTRA_TEXT, "Here's your comma seperated email bro");
				i.putExtra(Intent.EXTRA_STREAM, u1);
				
				try {
				    startActivity(Intent.createChooser(i, "Send mail..."));
					//Toast.makeText(HistoryListViewActivity.this, csv_Format.substring(0, 200), Toast.LENGTH_SHORT).show();
				} catch (android.content.ActivityNotFoundException ex) {
				    Toast.makeText(HistoryListViewActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
				}
			}else {
				Toast.makeText(HistoryListViewActivity.this, "Email UnVerified", Toast.LENGTH_SHORT).show();
			}
		// end export menu item selection 	
		} else if(item.getItemId() == R.id.log_out){

			new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Log out")
	        .setMessage("Are you sure you want to log out?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
	        	@Override
	        	public void onClick(DialogInterface dialog, int which) 
	        	{ 
	        		Intent intentMain = new Intent(HistoryListViewActivity.this, MainActivity.class);
	        		ParseUser.logOut();
	        		startActivityForResult(intentMain, 0);
	        		finish();
	        	} }).setNegativeButton("No", null).show();
			
		} // end else if
			
		return super.onOptionsItemSelected(item);
	}// end onOptionsItemSelected
	
	private class AsyncListLoader extends AsyncTask<String, Void, ArrayList<localExpense>>{
		private final ProgressDialog dialog = new ProgressDialog(HistoryListViewActivity.this);
		
		@Override
		protected ArrayList<localExpense> doInBackground(String... params) {
			DBAdapter adaptDB = new DBAdapter();
			return adaptDB.getExpenseList();
		}
		
		@Override
		protected void onPostExecute(ArrayList<localExpense> result){
			super.onPostExecute(result);
			adapter.setExpenseList(result);
			adapter.notifyDataSetChanged();
		}
		
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
		}
		
	}
	
	
	
	private void  updateExpenseList()
	{
		adapter=null;  // Must refresh the adapters items.
		DBAdapter adaptDB = new DBAdapter();
		adapter = new SimpleAdapter(this, user, adaptDB.getExpenseList());//POSSIBLE HAZARD IF NULL
		
		list.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		this.recreate();
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
			(new AsyncListLoader()).execute("");
			//updateExpenseList();
		}
	}

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

	    }).setNegativeButton("No", null).show();
	}//end onBackPressed()
	
	
	
	public String formatCSV(ArrayList<localExpense> expList){
		int iterExpenses;
		ParseUser locUser = ParseUser.getCurrentUser();
		String csvStr = "User," + locUser.getUsername() + "\n";
		csvStr += "Vendor,Purchase Date,Amount,Approved,notes\n";
		
		for(iterExpenses = 0; iterExpenses < expList.size(); iterExpenses++){
			localExpense insertExpense = new localExpense();
			insertExpense = expList.get(iterExpenses);
			csvStr += insertExpense.getVendor() + "," + insertExpense.getDate() + "," 
				+ Double.toString(insertExpense.getAmount()) + "," + insertExpense.getStatus() 
				+ ","+ insertExpense.getNotes() +"\n";	
		}
		return csvStr;
	}// end formatCSV
	
	// Precondition: root is a storage location to store a file.  format is a non empty string
	// Postcondition: Uri address of a created file from 'format' has been created at this Uri location
	public Uri fileURI(File root, String format){ 
		File file = null;
		if (root.canWrite()){
		    File dir    =   new File (root.getAbsolutePath() + "/PersonData");
		     dir.mkdirs();
		     file   =   new File(dir, "Data.csv");
		     FileOutputStream out   =   null;
		    try {
		        out = new FileOutputStream(file);
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
		        try {
		            out.write(format.getBytes());
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        try {
		            out.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		Uri u1  =   null;
		u1  =   Uri.fromFile(file);
		return u1;
	}// end fileURI
	
	
}//END HistoryListViewActivity
