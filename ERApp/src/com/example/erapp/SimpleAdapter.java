package com.example.erapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class SimpleAdapter extends BaseAdapter
{
	private ArrayList<localExpense> arraylist;	
	//private List<Expense> expenseList;
	List<ParseObject> ob;
	Context context;
	LayoutInflater inflater;
	ParseUser user;
	
	
	public class ViewHolder 
	{
	    TextView approved;
	    TextView vendor;
	    TextView ammount;
	    TextView purchaseDate;
	    TextView note;
	    ParseImageView receipt;
	    ImageView listImage;
	}
	
	public void setExpenseList(ArrayList<localExpense> expenseArrayList){
		this.arraylist = expenseArrayList;
	}
	
	public SimpleAdapter(Context context, final ParseUser user, ArrayList<localExpense> array)
	{
		this.user = user;
		inflater = LayoutInflater.from(context);
		this.context = context;
	    this.arraylist = array;
	    
	    
	}//end SimpleAdapter constructor
	
	@Override
	public int getCount() 
	{
		return arraylist.size();
	}
	
	@Override
	public long getItemId(int position) 
	{
		return position;
	}
	
	@Override
	public localExpense getItem(int position) 
	{
		return arraylist.get(position);
    }// end getItem
	


	public View getView(final int position, View v, ViewGroup parent) 
	{
		final ViewHolder holder;
		if( v == null)
		{
			holder = new ViewHolder();
			v = inflater.inflate(R.layout.list_item, null);
			
			holder.purchaseDate = (TextView)v.findViewById(R.id.secondLine);
			holder.vendor = (TextView)v.findViewById(R.id.firstLine);
			holder.ammount = (TextView)v.findViewById(R.id.ammountLine);
			holder.approved = (TextView)v.findViewById(R.id.approvedLine);
			holder.listImage = (ImageView)v.findViewById(R.id.icon);
			v.setTag(holder);
		}
		else 
		{
		    holder = (ViewHolder) v.getTag();
		 }
		
		
		holder.purchaseDate.setText(arraylist.get(position).getDate());
		holder.vendor.setText(arraylist.get(position).getVendor());
		holder.ammount.setText( "$"+Double.toString(arraylist.get(position).getAmount()));
		holder.approved.setText(arraylist.get(position).getStatus());
		holder.listImage.setImageBitmap(arraylist.get(position).getPaymentType());
		
		
		
		v.setOnClickListener(new OnClickListener() 
		{
			 public void onClick(View arg0) 
		      {
		        // Send single item click data to ViewExpenseActivity Class
		        Intent intent = new Intent(context, ViewExpenseActivity.class);
		        // Pass all data Vendor
		        intent.putExtra("Vendor",
		            (arraylist.get(position).getVendor()));
		        // Pass all data purchaseDate
		        intent.putExtra("purchaseDate",
		            (arraylist.get(position).getDate()));
		        // Pass all data amount
		        intent.putExtra("amount",
		            (Double.toString(arraylist.get(position).getAmount())));
		        // Pass all data pending
		        intent.putExtra("pending",
		            (arraylist.get(position).getStatus()));
		       // Pass all data note
		        
		        intent.putExtra("note",
			            (arraylist.get(position).getNotes()));
		        
		        intent.putExtra("objectId",
			            (arraylist.get(position).getObjectId()));
		        try
		        {
		        	if(arraylist.get(position).getPhotoFile() != null)
		        	{
		        		System.out.println("it wasn't null!");
		        		intent.putExtra("image",
					        (arraylist.get(position).getPhotoFile()).getData());
		        	}
				} catch (ParseException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        // Start SingleItemView Class
		        context.startActivity(intent);
		      }
		    });
		return v;
	}// end getView

}// end SimpleAdapter
