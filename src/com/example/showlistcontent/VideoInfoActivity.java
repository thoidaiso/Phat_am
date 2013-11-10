package com.example.showlistcontent;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.example.phat_am.R;
import com.example.phat_am.R.drawable;
import com.example.phat_am.R.id;
import com.example.phat_am.R.layout;
import com.example.phat_am.VideoActivity;

public class VideoInfoActivity extends SherlockActivity{
	
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
		Log.v("get link in video info",link);
		setContentView(R.layout.videoinfo);
		
		image = (ImageView)findViewById(R.id.videoinfo_image);
		image.setImageResource(R.drawable.phat);
		
		videoname = (TextView)findViewById(R.id.videoinfo_video_name);
		videoname.setText("Tụng 'Kinh Hồng Danh Sám Hối' (Có Phụ Đề)");
		
		videoauthor = (TextView)findViewById(R.id.videoinfo_video_author);
		videoauthor.setText("Thích Giác Hóa");
		
		list_chap = (ListView)findViewById(R.id.videoinfo_list_video);
		list_data.add("Chương 1");
		list_data.add("Chương 2");
		list_data.add("Chương 3");
		list_data.add("Chương 4");
		adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_row_simple, list_data);
		list_chap.setAdapter(adapter);
		list_chap.setBackgroundColor(getResources().getColor(R.color.list_row_green));
		list_chap.setOnItemClickListener(OnItemClick);
	}
	
	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.v("Click item chapter",""+arg2);
			Intent i = new Intent(getApplicationContext(), VideoActivity.class);
			i.putExtra("link", list_data.get(arg2).toString());
            startActivity(i);
		}
	};
	
}
