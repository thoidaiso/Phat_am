package com.example.showlistcontent;


import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;
import com.example.listview.CategoryAdapter;
import com.example.listview.Model_Category;
import com.example.phat_am.R;

public class category_fragment extends SherlockFragment{

	public static ArrayList<Model_Category> list_model = new ArrayList<Model_Category>();
	ListView list;
	CategoryAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list, container, false);
		
		String[] list_category = getResources().getStringArray(R.array.detail_category_array);
		
		for(int i=0; i<list_category.length; i++)
		{
			list_model.add(new Model_Category(list_category[i]));
		}
		
		ListView list = (ListView)rootView.findViewById(R.id.list_view);
		adapter = new CategoryAdapter(getSherlockActivity(), list_model);
		list.setAdapter(adapter);
		return rootView;
	}
	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		Log.v("go to create category", "aaaaaaa");
//		super.onCreate(savedInstanceState);
//		View rootView = inflater.inflate(R.layout.list, container, false);
//		
//		String[] list_category = getResources().getStringArray(R.array.detail_category_array);
//		
//		for(int i=0; i<list_category.length; i++)
//		{
//			list_model.add(new Model_Category(list_category[i]));
//		}
//		
//		ListView list = (ListView)findViewById(R.id.list_view);
//		adapter = new CategoryAdapter(this, list_model);
//		list.setAdapter(adapter);
//	}
		
//		list.setOnItemClickListener(onItemClick);
//		list.setOnItemLongClickListener(onLongItemClick);
	
	
	
//	private OnItemClickListener onItemClick = new OnItemClickListener() {
//
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//				long arg3) {
//			// TODO Auto-generated method stub
//			Log.v("====move===", "week:"+ list_week.get(arg2));
//			Fragment fragment = new Meal_planner_week_planning_fragment(list_week.get(arg2));
//			android.support.v4.app.FragmentManager fragmentManager = getSherlockActivity().getSupportFragmentManager();
//		    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
//		    Log.v("====done move===", "week:"+ list_week.get(arg2));
//		}
//	};
	
//	private OnItemLongClickListener onLongItemClick = new OnItemLongClickListener() {
//
//		@Override
//		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//				int position, long arg3) {
//			// TODO Auto-generated method stub
//			list_data.remove(position);
//			list_week.remove(position);
//			adapter.notifyDataSetChanged();
//			return true;
//		}
//	};
	
		
}
