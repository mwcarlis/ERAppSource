package com.example.erapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


public class SimpleAdapter extends ParseQueryAdapter<Expense> {
		
	public SimpleAdapter(Context context, final ParseUser user){
		super(context, new ParseQueryAdapter.QueryFactory<Expense>() {
			public ParseQuery<Expense> create() {
				ParseQuery query = new ParseQuery("Expense");
				query.orderByAscending("createdAt");
				query.whereEqualTo("user", user);
				return query;
			}
		});
	}//end SimpleAdapter constructor
	

	@Override
	public View getItemView(Expense expense, View v, ViewGroup parent) {
	
		if( v == null){
			v = View.inflate(getContext(), R.layout.list_item, null);
		}
		super.getItemView(expense, v, parent);
		
		TextView purchaseDate = (TextView)v.findViewById(R.id.secondLine);
		TextView seller = (TextView)v.findViewById(R.id.firstLine);
		TextView ammount = (TextView)v.findViewById(R.id.ammountLine);
		TextView approved = (TextView)v.findViewById(R.id.approvedLine);
		//ImageView paymentType = (ImageView)vi.findViewById(R.id.icon);
		
		
		purchaseDate.setText(expense.getExpenseDate());
		seller.setText(expense.getVendor());
		ammount.setText( Double.toString(expense.getAmount()));
		approved.setText(expense.getApproved());

		

		return v;
	}
	
}
