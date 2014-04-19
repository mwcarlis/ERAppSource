package com.example.erapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.ParseQueryAdapter;

public class HistoryListViewActivity extends Activity {

	static final String KEY_SELLER="seller";
	static final String KEY_PURCHASE_DATE="purchase_date";
	static final String KEY_SALE_AMMOUNT="sale_ammount";
	static final String KEY_APPROVAL="approval";
	
	static final String PURCHASE_DATE="Purchase Date: ";
	static final String USD="$";
	
	ListView list;
	//private ParseQueryAdapter<Expense> mainAdapter;
	SimpleAdapter adapter;
	

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_list);
		
		Intent intent = getIntent();
		list=(ListView)findViewById(R.id.list);

		adapter = new SimpleAdapter(this);
		
		
		list.setAdapter(adapter);
	}//END onCreate
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_history_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == R.id.menu_title){

		}else if(item.getItemId() == R.id.action_new){
			newExpense();
		}
		return super.onOptionsItemSelected(item);
	}// end onOptionsItemSelected
	
	private void  updateExpenseList(){
		adapter.loadObjects();
		list.setAdapter(adapter);
	}
	
	private void newExpense(){
		Intent i = new Intent(HistoryListViewActivity.this, NewExpenseActivity.class);
		startActivityForResult(i, 0);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if(resultCode == Activity.RESULT_OK){
			updateExpenseList();
		}
	}
	
}//END HistoryListViewActivity
