package com.example.listview;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phat_am.R;

public class ListVideoAdapter extends ArrayAdapter<VideoItem> {

	private final Activity context;
	private List<VideoItem> list;

	static class ViewHolder {
		public TextView name;
		public TextView author;
		public ImageView image;
	}
	public ListVideoAdapter(Activity context, List<VideoItem> list_model) {
		// TODO Auto-generated constructor stub
		super(context, R.layout.video_list_item, list_model);
		this.context = context;
		this.list = list_model;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder viewHolder;
		if (rowView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.video_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.name = (TextView) rowView
					.findViewById(R.id.video_list_title);
			viewHolder.author = (TextView) rowView
					.findViewById(R.id.video_list_astist);
			viewHolder.image = (ImageView) rowView
					.findViewById(R.id.video_list_image);
			rowView.setTag(viewHolder);
		}
		// Log.v("get view of video", "2");
		// rowView.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// ViewHolder viewHold=(ViewHolder)v.getTag();
		// Model_Category element = (Model_Category)viewHold.check.getTag();
		// element.setSelected(!((Model_Category)viewHold.check.getTag()).getSelected());
		// viewHold.check.setChecked(element.getSelected());
		//
		// // Toast.makeText(context,
		// "You choose:"+((Model)viewHold.check.getTag()).getName()+";isChecked:"+((Model)viewHold.check.getTag()).getSelected(),
		// Toast.LENGTH_SHORT).show();
		// }
		// });
		viewHolder = (ViewHolder) rowView.getTag();
		Log.v("Artist Videos", list.get(position).getArtist());

		Log.v("Title", list.get(position).getVideoTitle());
		Log.v("Image", list.get(position).getYoutubeImage());
		viewHolder.name.setText(list.get(position).getVideoTitle());
		// Log.v("get view of video", "4");
		viewHolder.author.setText("Tác giả: " + list.get(position).getArtist());
		// Log.v("get view of video", "5");

		try {
			Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(
					list.get(position).getYoutubeImage()).getContent());
			viewHolder.image.setImageBitmap(bitmap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Log.v("get view of video", "6");
		if (position % 2 == 0) {
			rowView.setBackgroundColor(context.getResources().getColor(
					R.color.list_row_green));
		} else {
			rowView.setBackgroundColor(context.getResources().getColor(
					R.color.white));
		}
		Log.v("RowView======", Integer.toString(position));
		return rowView;
	}

}