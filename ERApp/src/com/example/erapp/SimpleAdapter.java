package com.example.erapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.media.ImageReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


public class SimpleAdapter extends BaseAdapter
{
	private ArrayList<Expense> arraylist;	
	//private List<Expense> expenseList;
	List<ParseObject> ob;
	Context context;
	LayoutInflater inflater;
	ParseUser user;
	//ImageView
	
	public class ViewHolder 
	{
	    TextView approved;
	    TextView vendor;
	    TextView ammount;
	    TextView purchaseDate;
	    TextView note;
	    ParseImageView receipt;
	}
	
	public SimpleAdapter(Context context, final ParseUser user)
	{
		/*super(context, new ParseQueryAdapter.QueryFactory<Expense>() {
			public ParseQuery<Expense> create() {
				ParseQuery query = new ParseQuery("Expense");
				query.orderByAscending("createdAt");
				query.whereEqualTo("user", user);
				return query;
			}
		});*/
		//this.expenseList = new expenseList;
		this.user = user;
		inflater = LayoutInflater.from(context);
		this.context = context;
		
	    this.arraylist = new ArrayList<Expense>();
	    
	    try 
	    {
	    	ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
	            "Expense");
	    	query.orderByAscending("createdAt");
	    	query.whereEqualTo("user", user);
			ob = query.find();
			for (ParseObject currentExpense : ob)
			{
				
				ParseFile image = (ParseFile) ((Expense) currentExpense).getPhotoFile();
				Expense current = new Expense();
				current.setAuthor(user);
				current.setExpenseDate(((Expense) currentExpense).getExpenseDate());
				current.setVendor(((Expense) currentExpense).getVendor());
				current.setAmount(((Expense) currentExpense).getAmount());
				current.setNotes(((Expense) currentExpense).getNotes());
				current.setApproved(((Expense) currentExpense).getApproved());
				current.setObjectId(currentExpense.getObjectId());
				if(image != null)
				{
					current.setPhotoFile(image);
				}
				
				arraylist.add(current);
				
			}
			Collections.reverse(arraylist);
			
			
		} 
	    catch (ParseException e) 
	    {
			e.printStackTrace();
		}
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
	 public Expense getItem(int position) 
	 {
		  return arraylist.get(position);
      }
	


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
			
			v.setTag(holder);
		}
		else 
		{
		    holder = (ViewHolder) v.getTag();
		 }
		
		
		//super.getItemView(expense, v, parent);
		
		/*TextView purchaseDate = (TextView)v.findViewById(R.id.secondLine);
		TextView seller = (TextView)v.findViewById(R.id.firstLine);
		TextView ammount = (TextView)v.findViewById(R.id.ammountLine);
		TextView approved = (TextView)v.findViewById(R.id.approvedLine);*/
		//ImageView paymentType = (ImageView)vi.findViewById(R.id.icon);
		
		holder.purchaseDate.setText(arraylist.get(position).getExpenseDate());
		holder.vendor.setText(arraylist.get(position).getVendor());
		holder.ammount.setText( Double.toString(arraylist.get(position).getAmount()));
		holder.approved.setText(arraylist.get(position).getApproved());
		
		
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
		            (arraylist.get(position).getExpenseDate()));
		        // Pass all data amount
		        intent.putExtra("amount",
		            (Double.toString(arraylist.get(position).getAmount())));
		        // Pass all data pending
		        intent.putExtra("pending",
		            (arraylist.get(position).getApproved()));
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
	}

	/*public View getView(int arg0, View arg1, ViewGroup arg2) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
