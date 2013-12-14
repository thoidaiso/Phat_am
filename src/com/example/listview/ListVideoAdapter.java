package com.example.listview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phat_am.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ListVideoAdapter extends ArrayAdapter<VideoItem> {

	private final Activity context;
	private List<VideoItem> list;
	DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	protected ImageLoader imageLoader = ImageLoader.getInstance();

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
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();
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

		viewHolder = (ViewHolder) rowView.getTag();
		viewHolder.name.setText(list.get(position).getVideoTitle());
		viewHolder.author.setText("Tác giả: " + list.get(position).getArtist());
		imageLoader.displayImage(list.get(position).getYoutubeImage(),
				viewHolder.image, options, animateFirstListener);

		if (position % 2 == 0) {
			rowView.setBackgroundColor(context.getResources().getColor(
					R.color.list_row_green));
		} else {
			rowView.setBackgroundColor(context.getResources().getColor(
					R.color.white));
		}
		return rowView;
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}