package com.example.listview;

import java.util.ArrayList;

import com.example.listview.CustomGridViewAdapter.RecordHolder;
import com.example.phat_am.R;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ListVideoAdapter extends ArrayAdapter<Model_Video>{
	
	private final Activity context;
	private  ArrayList<Model_Video> list;
	static class ViewHolder {
	    public TextView name;
	    public TextView author;
	    public ImageView image;
	}
	public ListVideoAdapter(Activity context, ArrayList<Model_Video> list) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.video_list_item, list);
		this.context = context;
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.getView(position, convertView, parent);
		Log.v("get view of video", "1123123123");
		View rowView=convertView;
		ViewHolder viewHolder;
		if(rowView == null)
		{
			LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView=inflater.inflate(R.layout.video_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) rowView.findViewById(R.id.video_list_title);
			viewHolder.author = (TextView) rowView.findViewById(R.id.video_list_astist);
			viewHolder.image = (ImageView) rowView.findViewById(R.id.video_list_image);
			rowView.setTag(viewHolder);
		  } 
		Log.v("get view of video", "2");
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
		viewHolder=(ViewHolder)rowView.getTag();
		Log.v("get view of video", list.get(position).getName());
		viewHolder.name.setText(list.get(position).getName().toString());
		Log.v("get view of video", "4");
		viewHolder.author.setText("Tác giả: "+ list.get(position).getAuthor().toString());
		Log.v("get view of video", "5");
		viewHolder.image.setImageBitmap(list.get(position).getImage());
		Log.v("get view of video", "6");
		if (position %2 ==0)
		{
			rowView.setBackgroundColor(context.getResources().getColor(R.color.list_row_green));
		}
		else
		{
			rowView.setBackgroundColor(context.getResources().getColor(R.color.white));
		}
		return rowView;
	}
	
}