package com.example.erapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SimpleAdapter extends BaseAdapter {
	
	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater=null;
	//public ImageLoader imageLoader;
	
	public SimpleAdapter(Activity a, ArrayList<HashMap<String, String>> d){
		activity = a;
		data = d;
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//imageLoader=new ImageLoader(activity.getApplicationContext());	
	}
	@Override
	public int getCount() {
		return data.size();
	}
	@Override
	public Object getItem(int position) {
		return position;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi=convertView;
		if(convertView==null)
			vi = inflater.inflate(R.layout.list_item, null);

		
		TextView purchaseDate = (TextView)vi.findViewById(R.id.secondLine);
		TextView seller = (TextView)vi.findViewById(R.id.firstLine);
		TextView ammount = (TextView)vi.findViewById(R.id.ammountLine);
		TextView approved = (TextView)vi.findViewById(R.id.approvedLine);
		
		//ImageView paymentType = (ImageView)vi.findViewById(R.id.icon);
		
		
		HashMap<String, String> employee = new HashMap<String, String>();
		employee = data.get(position);
		
		purchaseDate.setText(employee.get(HistoryListViewActivity.KEY_PURCHASE_DATE));
		seller.setText(employee.get(HistoryListViewActivity.KEY_SELLER));
		ammount.setText(employee.get(HistoryListViewActivity.KEY_SALE_AMMOUNT));
		approved.setText(employee.get(HistoryListViewActivity.KEY_APPROVAL));
				
		return vi;
	}
	
}
