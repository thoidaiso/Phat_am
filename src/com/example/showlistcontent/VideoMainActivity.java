package com.example.showlistcontent;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.listview.ListVideoAdapter;
import com.example.listview.VideoItem;
import com.example.phat_am.R;

public class VideoMainActivity extends SherlockFragmentActivity {

	ViewPager pager;
	static int NUM_PAGES = 3;
	public ArrayList<VideoItem> list_model = new ArrayList<VideoItem>();
	ListView list;
	ListVideoAdapter adapter;
	String type;// top,new,random video
	int currentPage;

	public String getUrl() {
		return this.type;
	}

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);

		Intent intent = getIntent();
		type = intent.getStringExtra("type");
		String isOnline = intent.getStringExtra("isOnline");
		setContentView(R.layout.viewpager_titlestrip_activity);
		pager = (ViewPager) findViewById(R.id.pager);
		final FragmentPagerAdapter adapter = new FragmentPagerAdapter(
				getSupportFragmentManager(), this);
		pager.setAdapter(adapter);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageSelected(int position) {
				pager.setCurrentItem(position, true);
				adapter.getItem(pager.getCurrentItem()).getView();

			}

		});
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

	public class FragmentPagerAdapter extends
			android.support.v4.app.FragmentPagerAdapter {
		VideoMainActivity mActivity;
		private ArrayList<Fragment> fragments;

		public FragmentPagerAdapter(FragmentManager fm, VideoMainActivity ac) {
			super(fm);
			mActivity = ac;
		}

		@Override
		public int getItemPosition(Object object) {
			return pager.getCurrentItem();
		}
		
		@Override
		public Fragment getItem(int position) {
			Fragment fr;
			Log.v("Position xx", Integer.toString(position));
			if (position == 0) {
				fr = new loadmore_video_fragment(mActivity.getUrl(),
						"site_views");
			} else if (position == 1) {
				fr = new loadmore_video_fragment(mActivity.getUrl(), "added");
			} else {
				fr = new loadmore_video_fragment(mActivity.getUrl(),
						"video_title");

			}
			return fr;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return NUM_PAGES;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			// return super.getPageTitle(position);
			if (position == 0) {
				return getString(R.string.type_video_rating);
			} else if (position == 1) {
				return getString(R.string.type_video_date);
			} else if (position == 2) {
				return getString(R.string.type_video_title);
			}
			return ("");
		}

	}

	/*
	 * @SuppressLint("ValidFragment") public class ViewPagerAdapter extends
	 * Fragment {
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { // TODO Auto-generated method
	 * stub View rootView = inflater.inflate(R.layout.list, container, false);
	 * Bundle bun = getArguments(); int value = bun.getInt("key"); CharSequence
	 * sequence = getString(R.string.string) + value; ((TextView)
	 * rootView.findViewById(R.id.text)).setText(sequence); return rootView; }
	 * 
	 * }
	 */

}
