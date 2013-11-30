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

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.listview.CategoryAdapter;
import com.example.listview.ListVideoAdapter;
import com.example.listview.Model_Category;
import com.example.listview.Model_Video;
import com.example.phat_am.R;

public class list_video_activity extends SherlockActivity{

	public ArrayList<Model_Video> list_model = new ArrayList<Model_Video>();
	ListView list;
	ListVideoAdapter adapter;
	static String type;//top,random,new
	static String order; //order by date,title,rating
	static int limit; //limit video will be show
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bun = getIntent().getExtras();
		type = bun.getString("type");
		order = bun.getString("order");
		limit = bun.getInt("limit");
		
		setContentView(R.layout.list);
		String[] list_video = getResources().getStringArray(R.array.detail_video_array);
		String[] list_author = getResources().getStringArray(R.array.detail_video_author_array);
		
		Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.image);
		
		for(int i=0; i<5; i++)
		{
			list_model.add(new Model_Video(bm, list_video[i], list_author[i]));
		}
		
		ListView list = (ListView)findViewById(R.id.list_view);
		adapter = new ListVideoAdapter(this, list_model, 10);
		list.setAdapter(adapter);
		list.setOnItemClickListener(OnItemClick);
	}
	
	
	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.v("u click",list_model.get(arg2).getName().toString());
			Intent i = new Intent(getApplicationContext(), VideoInfoActivity.class);
			i.putExtra("link", list_model.get(arg2).getName().toString());
            startActivity(i);
		}
	};
	
		
}
