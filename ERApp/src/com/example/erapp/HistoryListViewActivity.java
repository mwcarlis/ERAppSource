package com.example.erapp;

import java.util.ArrayList;
import java.util.HashMap;

import com.parse.Parse;
import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class HistoryListViewActivity extends Activity {

	static final String KEY_SELLER="seller";
	static final String KEY_PURCHASE_DATE="purchase_date";
	static final String KEY_SALE_AMMOUNT="sale_ammount";
	static final String KEY_APPROVAL="approval";
	
	static final String PURCHASE_DATE="Purchase Date: ";
	static final String USD="$";
	
	ListView list;
	SimpleAdapter adapter;
	public String[] sellerList = {"Home Depot","7 Eleven","Costco","Outback Steak House","Philz Coffee", "Taco Bell", "Walgreens", "K Mart", "Frys", "Lees Sandwiches", "Super Taco"};
	public String[] purchaseDateList={"1/12/13", "1/23/13","2/16/13", "2/17/13", "2/18/13","2/19/13","2/20/13","2/21/13","2/22/13","2/23/13","2/24/13"};
	public String[] saleAmmountList={"1023.10","243.98","107.54","45.88","21.99","2.99","43.13","31.22","15.66","45.99","6.00"};
	public String[] approvedPendingList={"Approved","Pending","Approved","Approved","Rejected","Approved","Pending","Pending","Pending","Approved","Approved"};
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_list);
		Parse.initialize(this, "LZreCmKLdoTzWHFIBYBtGqiU2vqhioxNDPsqlmFH", "NOZLD2078ZCYverfSOgHhp4GA3AfkvyTcrCB0zbY");
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
		
		ArrayList<HashMap<String,String>> expenseList=new ArrayList<HashMap<String, String>>();
		Intent intent = getIntent();
		
		for(int i=0; i<11; i++)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(KEY_SELLER, sellerList[i]);
			map.put(KEY_PURCHASE_DATE, PURCHASE_DATE+purchaseDateList[i]);
			map.put(KEY_SALE_AMMOUNT, USD+saleAmmountList[i]);
			map.put(KEY_APPROVAL, approvedPendingList[i]);
			
			expenseList.add(map);
		}
		
		ListView list=(ListView)findViewById(R.id.list);
		
		adapter=new SimpleAdapter(this, expenseList);
		list.setAdapter(adapter);
	}//END onCreate
}//END HistoryListView