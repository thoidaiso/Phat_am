package com.example.showlistcontent;


import java.util.ArrayList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.listview.ListVideoAdapter;
import com.example.listview.Model_Video;
import com.example.phat_am.R;

public class VideoMainActivity extends SherlockFragmentActivity{

	ViewPager pager;
	static int NUM_PAGES = 3;
	public ArrayList<Model_Video> list_model = new ArrayList<Model_Video>();
	ListView list;
	ListVideoAdapter adapter;
	int type;//top,new,random video
	
//	public VideoMainActivity(int type)
//	{
//		this.type = type;
//	}
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		Bundle bun = getIntent().getExtras();
		if (bun != null)
			type = bun.getInt("type");
		Log.v("type==========", type+"");
		setContentView(R.layout.viewpager_titlestrip_activity);
		pager = (ViewPager)findViewById(R.id.pager);
		FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
//		pager.setCurrentItem(page);
	}
	
	
	
	public  class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter
	{

		public FragmentPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int postition) {
			// TODO Auto-generated method stub
//			if (postition == 1)
			{	
				Fragment fr = new loadmore_video_fragment("new", "rating");
				return fr;
			}
//			Fragment fr = new ViewPagerAdapter();
//			Bundle bun = new Bundle();
//			bun.putInt("key",postition);
//			fr.setArguments(bun);
//			return fr;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return NUM_PAGES;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
//			return super.getPageTitle(position);
			if (position == 0)
			{
				return getString(R.string.type_video_rating);
			}
			else if (position == 1)
			{
				return getString(R.string.type_video_date);
			}
			else if (position == 2)
			{
				return getString(R.string.type_video_title);
			}
			return ((CharSequence)"");
//			return "Section" + (position +1);
			
		}
		
	}
//	public  class ViewPagerAdapter extends Fragment
//	{
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			// TODO Auto-generated method stub
//			View rootView = inflater.inflate(R.layout.list, container, false);
//			Bundle bun = getArguments();
//			int value = bun.getInt("key");
//			CharSequence sequence = getString(R.string.string)+ value;
//			((TextView)rootView.findViewById(R.id.text)).setText(sequence);
//			return rootView;
//		}
//		
//	}
	
		
}
