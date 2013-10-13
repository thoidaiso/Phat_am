package com.example.listview;

import java.util.ArrayList;
import java.util.List;

import com.example.phat_am.R;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ListVideoAdapter extends ArrayAdapter<Model_Category>{
	
	private final Activity context;
	private  ArrayList<Model_Category> list;
	static class ViewHolder {
	    public TextView text;
	    public CheckBox check;
	}
	public ListVideoAdapter(Activity context, ArrayList<Model_Category> list) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.category_list_item, list);
		this.context = context;
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.getView(position, convertView, parent);
		View rowView=convertView;
		if(rowView == null)
		{
			LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView=inflater.inflate(R.layout.category_list_item, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) rowView.findViewById(R.id.category_list_text);
		}
//		rowView.setOnClickListener(new View.OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				ViewHolder viewHold=(ViewHolder)v.getTag();
//				Model_Category element = (Model_Category)viewHold.check.getTag();
//				element.setSelected(!((Model_Category)viewHold.check.getTag()).getSelected());
//				viewHold.check.setChecked(element.getSelected());
//				
////				Toast.makeText(context, "You choose:"+((Model)viewHold.check.getTag()).getName()+";isChecked:"+((Model)viewHold.check.getTag()).getSelected(), Toast.LENGTH_SHORT).show();
//			}
//		});
		ViewHolder viewHolder=(ViewHolder)rowView.getTag();
		viewHolder.text.setText(list.get(position).getName());
		return rowView;
	}
	
}