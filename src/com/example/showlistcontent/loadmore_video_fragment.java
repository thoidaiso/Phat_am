package com.example.showlistcontent;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;
import com.devspark.progressfragment.SherlockProgressFragment;
import com.example.listview.ListVideoAdapter;
import com.example.listview.VideoItem;
import com.example.phat_am.R;
import com.example.utils.Helper;
import com.example.utils.RestClient;
import com.loopj.android.http.JsonHttpResponseHandler;

@SuppressLint("ValidFragment")
public class loadmore_video_fragment extends SherlockProgressFragment {

	public ArrayList<VideoItem> list_model = new ArrayList<VideoItem>();
	ListView listview;
	private View mContentView;
	ListVideoAdapter adapter;
	String type;// top,random,new
	String order; // order by date,title,rating
	private Runnable mShowContentRunnable = new Runnable() {

		@Override
		public void run() {
			setContentShown(true);
		}

	};
	private Handler mHandler;

	public loadmore_video_fragment() {
		this.type = "topvideos";
		this.order = "added";
	}

	public loadmore_video_fragment(String type, String order) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.order = order;
		Log.v("type", "" + type);
		Log.v("order", "" + order);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setContentView(mContentView);
		setEmptyText(R.string.empty);
		obtainData(this.type, this.order);
	}

	private void obtainData(String type, String order) {
		setContentShown(false);
		RestClient.get(type, null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject result) {
				JSONArray jArray;
				try {
					jArray = result.getJSONArray("videos");
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject line_object = jArray.getJSONObject(i);
						// int id = line_object.getInt("id");
						String uniq_id = line_object.getString("uniq_id");
						String artist = line_object.getString("artist");
						String video_title = line_object
								.getString("video_title");
						String description = line_object
								.getString("description");
						String yt_thumb = line_object.getString("yt_thumb");
						int site_views = line_object.getInt("site_views");
						// String mp3 = line_object.getString("audio");
						String yt_id = line_object.getString("yt_id");
						VideoItem item = new VideoItem(uniq_id, artist,
								video_title, description, yt_id, yt_thumb,
								site_views);
						list_model.add(item);
						adapter.notifyDataSetChanged();

					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mHandler = new Handler();
		mHandler.postDelayed(mShowContentRunnable, 500);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mContentView = inflater.inflate(R.layout.loadmore_list, container,
				false);
		/*
		 * String[] list_video =
		 * getResources().getStringArray(R.array.detail_video_array); String[]
		 * list_author =
		 * getResources().getStringArray(R.array.detail_video_author_array);
		 */
		// Bitmap bm = BitmapFactory.decodeResource(this.getResources(),
		// R.drawable.image);
		// list_model.clear();
		/*
		 * for(int i=0; i<8; i++) { list_model.add(new Model_Video(bm,
		 * list_video[i], list_author[i])); }
		 */

		listview = (PullAndLoadListView) mContentView
				.findViewById(R.id.load_more_list_view);
		adapter = new ListVideoAdapter(getSherlockActivity(), list_model);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(OnItemClick);
		// Set a listener to be invoked when the list should be refreshed.
//		listview.setOnRefreshListener(new OnRefreshListener() {
//
//			@Override
//			public void onRefresh() {
//				// Do work to refresh the list here.
//				new PullToRefreshDataTask().execute();
//			}
//		});

		// set a listener to be invoked when the list reaches the end
//		listview.setOnLoadMoreListener(new OnLoadMoreListener() {
//
//			@Override
//			public void onLoadMore() {
//				// Do the work to load more items at the end of list
//				// here
//				new LoadMoreDataTask().execute();
//			}
//		});
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			// Log.v("u click",list_model.get(arg2).getName().toString());
			Intent i = new Intent(getSherlockActivity(),
					VideoInfoActivity.class);
			/*
			 * i.putExtra("link", list_model.get(arg2).getName().toString());
			 * startActivity(i);
			 */
		}
	};

//	private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {
//
//		@Override
//		protected Void doInBackground(Void... params) {
//
//			if (isCancelled()) {
//				return null;
//			}
//			// Simulates a background task
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//			}
//			// Put code to download list video data here, and add to list_model
//			/*
//			 * String[] list_video =
//			 * getResources().getStringArray(R.array.detail_video_array);
//			 * String[] list_author =
//			 * getResources().getStringArray(R.array.detail_video_author_array);
//			 */
//			Bitmap bm = BitmapFactory.decodeResource(getResources(),
//					R.drawable.image);
//
//			/*
//			 * for(int i=0; i<8; i++) { list_model.add(new Model_Video(bm,
//			 * list_video[i], list_author[i])); }
//			 */
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			// Todo: Delete 2 line after finish all
//			Bitmap bm = BitmapFactory.decodeResource(getResources(),
//					R.drawable.image);
//			// list_model.add(new Model_Video(bm, "Loadmore", "Loadmore"));
//
//			// We need notify the adapter that the data have been changed
//			adapter.notifyDataSetChanged();
//
//			// Call onLoadMoreComplete when the LoadMore task, has finished
//			listview.onLoadMoreComplete();
//			return;
//			// super.onPostExecute(result);
//		}
//
//		@Override
//		protected void onCancelled() {
//			// Notify the loading more operation has finished
//			listview.onLoadMoreComplete();
//		}
//	}
//
//	private class PullToRefreshDataTask extends AsyncTask<Void, Void, Void> {
//
//		@Override
//		protected Void doInBackground(Void... params) {
//
//			if (isCancelled()) {
//				return null;
//			}
//
//			// Simulates a background task
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//			}
//			// Put code to download list video data here, and add to list_model
//			String[] list_video = getResources().getStringArray(
//					R.array.detail_video_array);
//			String[] list_author = getResources().getStringArray(
//					R.array.detail_video_author_array);
//
//			Bitmap bm = BitmapFactory.decodeResource(getResources(),
//					R.drawable.image);
//			/*
//			 * for(int i=0; i<8; i++) { list_model.add(0,new Model_Video(bm,
//			 * list_video[i], list_author[i])); }
//			 */
//
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Void result) {
//			// Todo: Delete 2 line after finish all
//			Bitmap bm = BitmapFactory.decodeResource(getResources(),
//					R.drawable.image);
//			// list_model.add(0, new Model_Video(bm,
//			// "Add after pull to reffress", "Loadmore"));
//
//			// We need notify the adapter that the data have been changed
//			adapter.notifyDataSetChanged();
//
//			// Call onLoadMoreComplete when the LoadMore task, has finished
//			listview.onRefreshComplete();
//			// return;
//			super.onPostExecute(result);
//		}
//
//		@Override
//		protected void onCancelled() {
//			// Notify the loading more operation has finished
//			listview.onLoadMoreComplete();
//		}
//	}

}
