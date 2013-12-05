package com.example.showlistcontent;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.listview.VideoItem;
import com.example.phat_am.R;
import com.example.phat_am.VideoViewActivity;
import com.example.utils.RestClientUsage;

public class VideoInfoActivity extends SherlockActivity {

	ImageView image;
	ListView list_chap;
	ArrayAdapter<String> adapter;
	ArrayList<String> list_data = new ArrayList<String>();
	TextView videoname;
	TextView videoauthor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bun = getIntent().getExtras();
		String link = bun.getString("link");
		Log.v("get link in video info", link);
		setContentView(R.layout.videoinfo);

		image = (ImageView) findViewById(R.id.videoinfo_image);
		image.setImageResource(R.drawable.phat);

		videoname = (TextView) findViewById(R.id.videoinfo_video_name);
		videoname.setText("Tụng 'Kinh Hồng Danh Sám Hối' (Có Phụ Đề)");

		videoauthor = (TextView) findViewById(R.id.videoinfo_video_author);
		videoauthor.setText("Thích Giác Hóa");

		list_chap = (ListView) findViewById(R.id.videoinfo_list_video);
		list_data.add("Chương 1");
		list_data.add("Chương 2");
		list_data.add("Chương 3");
		list_data.add("Chương 4");
		adapter = new ArrayAdapter<String>(getApplicationContext(),
				R.layout.list_row_simple, list_data);
		list_chap.setAdapter(adapter);
		list_chap.setBackgroundColor(getResources().getColor(
				R.color.list_row_green));
		list_chap.setOnItemClickListener(OnItemClick);
		Log.v("array received", "xxxxxxxxxxxxxxxxxxxxxxxx");
		// RestClientUsage.getAllCategory();
//		RestClientUsage.getArtist("artist", 0);
//		 RestClientUsage.getMainVideos("topvideos");
//		 List<VideoItem> videos = new ArrayList<VideoItem>();
//		 List<VideoItem> videos = RestClientUsage.getVideoByCategory(501, "added", 0);
		RestClientUsage.getArtist("artist", 0);
		RestClientUsage.getArtist("cnt", 10);
//		 RestClientUsage.getVideoFullInfo(videos.get(0));
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.v("Click item chapter", "" + arg2);
			Intent i = new Intent(getApplicationContext(),
					VideoViewActivity.class);
			// i.putExtra("link", list_data.get(arg2).toString());
			startActivity(i);
		}
	};
	
//	protected void onDestroy() {
//		Log.v("on destroy actitity","--------------------------------");
//	}
	
//	@Override
//	public void finish() {
//		// TODO Auto-generated method stub
//		Log.v("on finish---","----------------");
//		super.finish();
//	}
//	
//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		Log.v("on stop","==================================");
//		super.onStop();
//	}

}
