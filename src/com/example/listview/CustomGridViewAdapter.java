package com.example.listview;


import java.util.ArrayList;

import com.example.phat_am.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * 
 * @author manish.s
 *
 */
public class CustomGridViewAdapter extends ArrayAdapter<GridItem> {
	 Context context;
	 int layoutResourceId;
	 ArrayList<GridItem> data = new ArrayList<GridItem>();
	
	 public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<GridItem> data) {
		  super(context, layoutResourceId, data);
		  this.layoutResourceId = layoutResourceId;
		  this.context = context;
		  this.data = data;
		 }
	
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
		  View row = convertView;
		  RecordHolder holder = null;
		
		  if (row == null) {
			   LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//			   row=inflater.inflate(R.layout.gridview, null);
			   row = inflater.inflate(layoutResourceId, parent, false);
			
			   holder = new RecordHolder();
//			   holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			   holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			   row.setTag(holder);
			  } else {
			   holder = (RecordHolder) row.getTag();
		  }
		
		  GridItem item = data.get(position);
//		  holder.txtTitle.setText(item.getTitle());
		  holder.imageItem.setImageBitmap(item.getImage());
		  return row;
	
	 }
	
	 static class RecordHolder {
//	  TextView txtTitle;
	  ImageView imageItem;
	
	 }
}
