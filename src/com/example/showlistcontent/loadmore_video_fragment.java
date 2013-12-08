package com.example.showlistcontent;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.costum.android.widget.PullAndLoadListView;
import com.costum.android.widget.PullAndLoadListView.OnLoadMoreListener;
import com.costum.android.widget.PullToRefreshListView.OnRefreshListener;
import com.example.listview.ListVideoAdapter;
import com.example.listview.VideoItem;
import com.example.phat_am.R;

@SuppressLint("ValidFragment")
public class loadmore_video_fragment extends SherlockFragment{

	public ArrayList<VideoItem> list_model = new ArrayList<VideoItem>();
	PullAndLoadListView listview;
	ListVideoAdapter adapter;
	static String type;//top,random,new
	static String order; //order by date,title,rating
	
	public loadmore_video_fragment(String type, String order) {
		// TODO Auto-generated constructor stub
		loadmore_video_fragment.type = type;
		loadmore_video_fragment.order = order;
		Log.v("video fragment type", ""+type);
		Log.v("order", ""+order);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.loadmore_list, container, false);
		/*
		String[] list_video = getResources().getStringArray(R.array.detail_video_array);
		String[] list_author = getResources().getStringArray(R.array.detail_video_author_array);
		*/
		Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.image);
//		list_model.clear();
		/*for(int i=0; i<8; i++)
		{
			list_model.add(new Model_Video(bm, list_video[i], list_author[i]));
		}*/
		listview = (PullAndLoadListView)rootView.findViewById(R.id.list_view);
		adapter = new ListVideoAdapter(getSherlockActivity(), list_model);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(OnItemClick);
//		Helper.getListViewSize(listview);
		// Set a listener to be invoked when the list should be refreshed.
		listview.setOnRefreshListener(new OnRefreshListener() {

					@Override
					public void onRefresh() {
						// Do work to refresh the list here.
						new PullToRefreshDataTask().execute();
					}
				});

		// set a listener to be invoked when the list reaches the end
		listview.setOnLoadMoreListener(new OnLoadMoreListener() {
					
					@Override
					public void onLoadMore() {
						// Do the work to load more items at the end of list
						// here
						new LoadMoreDataTask().execute();
					}
				});
		return rootView;
	}
	
	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
//			Log.v("u click",list_model.get(arg2).getName().toString());
			Intent i = new Intent(getSherlockActivity(), VideoInfoActivity.class);
			/*i.putExtra("link", list_model.get(arg2).getName().toString());
            startActivity(i);*/
		}
	};
	
	private class LoadMoreDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}
			// Simulates a background task
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			//Put code to download list video data here, and add to list_model
			String[] list_video = getResources().getStringArray(R.array.detail_video_array);
			String[] list_author = getResources().getStringArray(R.array.detail_video_author_array);
			
			Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image);
			
			/*for(int i=0; i<8; i++)
			{
				list_model.add(new Model_Video(bm, list_video[i], list_author[i]));
			}*/
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			//Todo: Delete 2 line after finish all
			Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image);
//			list_model.add(new Model_Video(bm, "Loadmore", "Loadmore"));

			// We need notify the adapter that the data have been changed
			adapter.notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			listview.onLoadMoreComplete();
			return ;
//			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			listview.onLoadMoreComplete();
		}
	}

	private class PullToRefreshDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			if (isCancelled()) {
				return null;
			}

			// Simulates a background task
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			//Put code to download list video data here, and add to list_model
			String[] list_video = getResources().getStringArray(R.array.detail_video_array);
			String[] list_author = getResources().getStringArray(R.array.detail_video_author_array);
			
			Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image);
			/*
			for(int i=0; i<8; i++)
			{
				list_model.add(0,new Model_Video(bm, list_video[i], list_author[i]));
			}*/

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			//Todo: Delete 2 line after finish all
			Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.image);
//			list_model.add(0, new Model_Video(bm, "Add after pull to reffress", "Loadmore"));

			// We need notify the adapter that the data have been changed
			adapter.notifyDataSetChanged();

			// Call onLoadMoreComplete when the LoadMore task, has finished
			listview.onRefreshComplete();
//			return;
			super.onPostExecute(result);
		}

		@Override
		protected void onCancelled() {
			// Notify the loading more operation has finished
			listview.onLoadMoreComplete();
		}
	}
	
		
}
