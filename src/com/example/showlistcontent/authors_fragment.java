package com.example.showlistcontent;


import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.listview.CategoryAdapter;
import com.example.listview.Model_Category;
import com.example.phat_am.R;

public class authors_fragment extends SherlockFragment{

	public static ArrayList<Model_Category> list_model = new ArrayList<Model_Category>();
	ListView list;
	CategoryAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list, container, false);
		
		String[] list_category = getResources().getStringArray(R.array.detail_authors_array);
		
		for(int i=0; i<list_category.length; i++)
		{
			list_model.add(new Model_Category(list_category[i]));
		}
		
		ListView list = (ListView)rootView.findViewById(R.id.list_view);
		adapter = new CategoryAdapter(getSherlockActivity(), list_model);
		list.setAdapter(adapter);
		return rootView;
	}
	
		
}
