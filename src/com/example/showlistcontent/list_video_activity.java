package com.example.showlistcontent;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.example.listview.ListVideoAdapter;
import com.example.listview.VideoItem;
import com.example.phat_am.R;

public class list_video_activity extends SherlockActivity {

	public ArrayList<VideoItem> list_model = new ArrayList<VideoItem>();
	ListView list;
	ListVideoAdapter adapter;
	static String type;// top,random,new
	static String order; // order by date,title,rating

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bun = getIntent().getExtras();
		type = bun.getString("type");
		order = bun.getString("order");
		Log.v("======================","xxxxxxxxxxxxx");
		setContentView(R.layout.list);
		String[] list_video = getResources().getStringArray(
				R.array.detail_video_array);
		String[] list_author = getResources().getStringArray(
				R.array.detail_video_author_array);

		Bitmap bm = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.image);

		/*
		 * for(int i=0; i<5; i++) { list_model.add(new Model_Video(bm,
		 * list_video[i], list_author[i])); }
		 */

		ListView list = (ListView) findViewById(R.id.list_view);
		adapter = new ListVideoAdapter(this, list_model);
		list.setAdapter(adapter);
		list.setOnItemClickListener(OnItemClick);
	}

	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.v("u click", list_model.get(arg2).getVideoTitle());
			Intent i = new Intent(getApplicationContext(),
					VideoInfoActivity.class);
			i.putExtra("link", list_model.get(arg2).getVideoTitle());
			startActivity(i);
		}
	};

}
