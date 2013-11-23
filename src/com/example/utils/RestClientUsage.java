package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.listview.ArtistItem;
import com.example.listview.CategoryItem;
import com.example.listview.VideoInfo;
import com.example.listview.VideoItem;
import com.loopj.android.http.JsonHttpResponseHandler;

@SuppressLint("DefaultLocale")
class RestClientUsage {
	int limit = 15;

	public List<CategoryItem> getAllCategory() throws JSONException {

		final List<CategoryItem> data = new ArrayList<CategoryItem>();

		RestClient.get("category", null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject result) {
				JSONArray jArray;
				try {
					jArray = result.getJSONArray("videos");
					Log.v("array received", "" + jArray.toString());
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject line_object = jArray.getJSONObject(i);
						int id = line_object.getInt("id");
						int parent_id = line_object.getInt("parent_id");
						String tag = line_object.getString("tag");
						String name = line_object.getString("name");
						int position = line_object.getInt("position");
						int total_videos = line_object.getInt("total_videos");
						CategoryItem item = new CategoryItem(id, parent_id,
								tag, name, position, total_videos);
						data.add(item);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return data;
	}
// get videos by category with ordeBy and offset
	@SuppressLint("DefaultLocale")
	public List<VideoItem> getVideoByCategory(int id, String orderBy,
			String offSet) {
		final List<VideoItem> data = new ArrayList<VideoItem>();
		String url = String.format("category/%d/%s/%d", id, orderBy, offSet);
		RestClient.get(url, null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject result) {
				JSONArray jArray;
				try {
					jArray = result.getJSONArray("videos");
					Log.v("array received", "" + jArray.toString());
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
						int mp3 = line_object.getInt("audio");
						String yt_id = line_object.getString("yt_id");
						VideoItem item = new VideoItem(uniq_id, artist,
								video_title, description, yt_id, yt_thumb,
								site_views);
						data.add(item);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return data;
	}

	// get Top, latest, random videos
	@SuppressLint("DefaultLocale")
	public List<VideoItem> getMainVideos(String url) {
		final List<VideoItem> data = new ArrayList<VideoItem>();
		RestClient.get(url, null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject result) {
				JSONArray jArray;
				try {
					jArray = result.getJSONArray("videos");
					Log.v("array received", "" + jArray.toString());
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
						int mp3 = line_object.getInt("audio");
						String yt_id = line_object.getString("yt_id");
						VideoItem item = new VideoItem(uniq_id, artist,
								video_title, description, yt_id, yt_thumb,
								site_views);
						data.add(item);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return data;
	}
	@SuppressLint("DefaultLocale")
	public List<ArtistItem> getArtist(String orderBy, int offSet) {
		final List<ArtistItem> data = new ArrayList<ArtistItem>();
		String url = String.format("artist/%s/%d", orderBy, offSet);
		RestClient.get(url, null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject result) {
				JSONArray jArray;
				try {
					jArray = result.getJSONArray("videos");
					Log.v("array received", "" + jArray.toString());
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject line_object = jArray.getJSONObject(i);
						int cnt = line_object.getInt("cnt");
						String artist = line_object.getString("artist");
						ArtistItem item = new ArtistItem(artist, cnt);
						data.add(item);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return data;
	}
}