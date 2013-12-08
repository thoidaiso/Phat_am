package com.example.showlistcontent;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.listview.Category_with_number_Adapter;
import com.example.listview.ListVideoAdapter;
import com.example.listview.Model_Category_with_number;
import com.example.listview.Model_Video;
import com.example.phat_am.R;
import com.example.utils.Helper;

public class home_fragment extends SherlockFragment{

	public ArrayList<Model_Category_with_number> list_model = new ArrayList<Model_Category_with_number>();
	ListView list_view_category;
	Category_with_number_Adapter adapter_category;
	
	public ArrayList<Model_Video> list_model_new_video = new ArrayList<Model_Video>();
	ListView list_view_new_video;
	ListVideoAdapter adapter_new_video;
	Button button[] = new Button[3];
	Button btn_more_video;
	ViewPager pager;
	static int NUM_PAGES = 3;
	static int VIDEO_PAGE = 1;
	
	@SuppressWarnings("deprecation")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.home_fragment, container, false);
		
//		Declare list view show info about how many category, author
		String[] list_category = getResources().getStringArray(R.array.list_category_array);
		String number = "";
		list_model.clear();
		for(int i=0; i<2; i++)
		{
			if (i==0)
				number = "11";
			if (i==1)
				number = "100";
			if (i>1)
				number = "";
			list_model.add(new Model_Category_with_number(list_category[i], number));
		}
		
		list_view_category = (ListView)rootView.findViewById(R.id.home_fragment_list_description);
		adapter_category = new Category_with_number_Adapter(getSherlockActivity(), list_model);
		list_view_category.setAdapter(adapter_category);
		Helper.getListViewSize(list_view_category);
		list_view_category.setOnItemClickListener(onCategoryItemClick);
		
		button[0] = (Button)rootView.findViewById(R.id.home_fragment_btn_show_top_video);
		button[1] = (Button)rootView.findViewById(R.id.home_fragment_btn_show_new_video);
		button[2] = (Button)rootView.findViewById(R.id.home_fragment_btn_show_random_video);
		for (int i=0; i<3; i++)
		{
			button[i].setOnClickListener(OnButtonClickListener);
			button[i].setBackgroundColor(getResources().getColor(android.R.color.background_light));
			button[i].setTag(i);
		}
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
			button[1].setBackground(getResources().getDrawable(R.drawable.red_gradient));
		else
			button[1].setBackgroundDrawable(getResources().getDrawable(R.drawable.red_gradient));
		
		Fragment fragment = new video_fragment("latestvideos", "added");
		FragmentManager childmanager = getFragmentManager();
		childmanager.beginTransaction().replace(R.id.home_fragment_fragment, fragment).commit();
		
		btn_more_video = (Button)rootView.findViewById(R.id.home_fragment_btn_show_more_video);
		btn_more_video.setOnClickListener(OnClickShowMoreVideo);
		return rootView;
		
	}
	
	private OnItemClickListener onCategoryItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			FragmentManager manger = getFragmentManager();
//			FragmentManager childmanager = getChildFragmentManager();
			Fragment fragment = null;
			String name = list_model.get(arg2).getName();
			if (name.equals("Chuyên mục"))
			{
				fragment = new category_fragment();
//				childmanager.beginTransaction().replace(R.id.home_fragment, fragment).addToBackStack(null).commit();
			}
			else if (name.equals("Tác giả"))
			{
				fragment = new authors_fragment();
//				childmanager.beginTransaction().replace(R.id.home_fragment, fragment).addToBackStack(null).commit();	
			}
//			else if (name.equals("Top Videos"))
//			{
//				fragment = new top_video_fragment();
//				manager.beginTransaction().replace(R.id.content_frame, fragment).commit();	
//			}
			manger.beginTransaction().replace(((ViewGroup)getView().getParent()).getId(), fragment).addToBackStack(null).commit();
		}
	};
	
	//Onclick button More (Them) to watch more video
	private View.OnClickListener OnClickShowMoreVideo = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//Top video
			if (VIDEO_PAGE == 0)
			{
				Intent i = new Intent(getSherlockActivity(), VideoMainActivity.class);
				i.putExtra("type", 0);
                startActivity(i);
			}
			//New Video
			else if (VIDEO_PAGE == 1)
			{
				Intent i = new Intent(getSherlockActivity(), VideoMainActivity.class);
				i.putExtra("type", 1);
                startActivity(i);
			}
			//Random Video
			else if (VIDEO_PAGE == 2)
			{
				Intent i = new Intent(getSherlockActivity(), VideoMainActivity.class);
				i.putExtra("type", 2);
                startActivity(i);
			}
		}
	};
	
	//When click buttons to show type of video
private View.OnClickListener OnButtonClickListener = new View.OnClickListener() {
		
		@SuppressWarnings("deprecation")
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			for (int i=0; i<3; i++)
				button[i].setBackgroundColor(getResources().getColor(android.R.color.background_light));
			if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
				v.setBackground(getResources().getDrawable(R.drawable.red_gradient));
			else
				v.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_gradient));
			VIDEO_PAGE = Integer.parseInt(v.getTag().toString());
			Fragment fragment = null;
			switch (VIDEO_PAGE) {
			case 0:
				fragment = new video_fragment("topvideos", "rating");
				break;
			case 1:
				fragment = new video_fragment("latestvideos", "rating");
				break;
			case 2:
				fragment = new video_fragment("randomvideos", "rating");
				break;
			default:
				break;
			}
			FragmentManager childmanager = getFragmentManager();
			childmanager.beginTransaction().replace(R.id.home_fragment_fragment, fragment).commit();
			Log.v("click button", v.getTag().toString());
		}
	};
	
		
}
