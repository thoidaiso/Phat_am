package com.example.showlistcontent;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.devspark.progressfragment.SherlockProgressFragment;
import com.example.listview.ListVideoAdapter;
import com.example.listview.VideoItem;
import com.example.phat_am.R;
import com.example.utils.Helper;
import com.example.utils.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

@SuppressLint("ValidFragment")
public class video_fragment extends SherlockProgressFragment {

	public List<VideoItem> list_model;
	ListView list;
	ListVideoAdapter adapter;
	public String type;// top,random,new
	public String order; // order by date,title,rating
	private View mContentView;
	private Handler mHandler;
	List<String> AlreadyLoad;
	private Runnable mShowContentRunnable = new Runnable() {

		@Override
		public void run() {
			setContentShown(true);
		}

	};

	public video_fragment() {
		this.type = "topvideos";
		this.order = "added";
		this.AlreadyLoad.add("topvideos");
	}

	public video_fragment(String type, String order) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.order = order;
		this.AlreadyLoad.add(type);
		Log.v("video fragment type", "" + type);
		Log.v("order", "" + order);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.list, null);
		this.list_model = new ArrayList<VideoItem>();
		list = (ListView) mContentView.findViewById(R.id.list_view);
		adapter = new ListVideoAdapter(getSherlockActivity(), list_model);
		list.setAdapter(adapter);
		list.setOnItemClickListener(OnItemClick);
//		Helper.getListViewSize(list);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.v("Created View", "===========");
		setContentView(mContentView);
		setEmptyText(R.string.empty);
		obtainData(this.type);
	}

	private void obtainData(String type) {
		// Show indeterminate progress
		setContentShown(false);
		Log.v("=======", type);
		
		RestClient.get("latestvideos", null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject result) {
				JSONArray jArray;
				Log.v("GET==================", "========");
				try {
					jArray = result.getJSONArray("videos");
					Log.v("Top videos", "" + jArray.length());
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject line_object = jArray.getJSONObject(i);
						int id = line_object.getInt("id");
						String uniq_id = line_object.getString("uniq_id");
						String artist = line_object.getString("artist");
						String video_title = line_object
								.getString("video_title");
						String description = line_object
								.getString("description");
						String yt_thumb = line_object.getString("yt_thumb");
						int site_views = line_object.getInt("site_views");
						String mp3 = line_object.getString("audio");
						String yt_id = line_object.getString("yt_id");
						VideoItem item = new VideoItem(uniq_id, artist,
								video_title, description, yt_id, yt_thumb,
								site_views);
						list_model.add(item);

					}
					Helper.getListViewSize(list);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mHandler = new Handler();
		mHandler.postDelayed(mShowContentRunnable, 3000);
	}

	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			// TODO Auto-generated method stub
			Log.v("u click", list_model.get(pos).getVideoTitle());
			Intent i = new Intent(getSherlockActivity(),
					VideoInfoActivity.class);
			i.putExtra("link", list_model.get(pos).getVideoId());
			startActivity(i);
		}
	};

}
