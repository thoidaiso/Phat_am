package com.example.showlistcontent;


import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.listview.CategoryAdapter;
import com.example.listview.CustomGridViewAdapter;
import com.example.listview.GridItem;
import com.example.listview.Model_Category;
import com.example.phat_am.R;

public class category_fragment extends SherlockFragment{

	GridView gridView;
	ArrayList<GridItem> gridArray = new ArrayList<GridItem>();
	CustomGridViewAdapter customGridAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.gridview, container, false);
		
		String[] list_category = getResources().getStringArray(R.array.detail_category_array);
		//set grid view item
		Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.clapperboard);
		for(int i=0; i<list_category.length; i++)
		{
			gridArray.add(new GridItem(homeIcon,list_category[i]));
		}
		

		gridView = (GridView) rootView.findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(getSherlockActivity(), R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
				
		return rootView;
	}
		
}

