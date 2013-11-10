package com.example.showlistcontent;


import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.listview.CategoryAdapter;
import com.example.listview.ListVideoAdapter;
import com.example.listview.Model_Category;
import com.example.listview.Model_Video;
import com.example.phat_am.R;

public class video_fragment extends SherlockFragment{

	public ArrayList<Model_Video> list_model = new ArrayList<Model_Video>();
	ListView list;
	ListVideoAdapter adapter;
	static int type;//top,random,new
	static int order; //order by date,title,rating
	
	public video_fragment(int type, int order) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.order = order;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list, container, false);
		
		String[] list_video = getResources().getStringArray(R.array.detail_video_array);
		String[] list_author = getResources().getStringArray(R.array.detail_video_author_array);
		
		Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.image);
		
		for(int i=0; i<5; i++)
		{
			list_model.add(new Model_Video(bm, list_video[i], list_author[i]));
		}
		
		ListView list = (ListView)rootView.findViewById(R.id.list_view);
		adapter = new ListVideoAdapter(getSherlockActivity(), list_model);
		list.setAdapter(adapter);
		list.setOnItemClickListener(OnItemClick);
		return rootView;
	}
	
	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.v("u click",list_model.get(arg2).getName().toString());
			Intent i = new Intent(getSherlockActivity(), VideoInfoActivity.class);
			i.putExtra("link", list_model.get(arg2).getName().toString());
            startActivity(i);
		}
	};
	
		
}
