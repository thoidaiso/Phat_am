package com.example.showlistcontent;


import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.listview.CustomGridViewAdapter;
import com.example.listview.GridItem;
import com.example.phat_am.R;

public class category_fragment extends SherlockFragment{

	GridView gridView;
	ArrayList<GridItem> gridArray = new ArrayList<GridItem>();
	CustomGridViewAdapter customGridAdapter;
	static int category_length =11;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.gridview, container, false);
		
//		String[] list_category = getResources().getStringArray(R.array.detail_category_array);
		//set grid view item
		int[] icon = {	R.drawable.icon_category1,
						R.drawable.icon_category2,
						R.drawable.icon_category3,
						R.drawable.icon_category4,
						R.drawable.icon_category5,
						R.drawable.icon_category6,
						R.drawable.icon_category7,
						R.drawable.icon_category8,
						R.drawable.icon_category9,
						R.drawable.icon_category10,
						R.drawable.icon_category11,
					 };
		for(int i=0; i<category_length; i++)
		{
			Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), icon[i]);
			gridArray.add(new GridItem(homeIcon));
		}
		

		gridView = (GridView) rootView.findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(getSherlockActivity(), R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
				
		return rootView;
	}
		
}

