package com.example.phat_am;



import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.example.showlistcontent.authors_fragment;
import com.example.showlistcontent.category_fragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
public class MainActivity extends SherlockFragmentActivity {

	SlidingMenu menu;
	ListView list;
	ArrayList<String> list_content_menu = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
		
		LayoutInflater inflater = getLayoutInflater();
        View menu_frame = inflater.inflate(R.layout.list, null);
        
		menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
        menu.setMenu(menu_frame);
        
        
		list = (ListView)menu_frame.findViewById(R.id.list_view);
        TwoTextArrayAdapter adapter = new TwoTextArrayAdapter(this, getListMenuItem());
        list.setAdapter(adapter);
        list.setOnItemClickListener(onItemClick);
        list.setDivider(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	private OnItemClickListener onItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			FragmentManager manager = getSupportFragmentManager();
			Fragment fragment = null;
			String name = list_content_menu.get(arg2);
			menu.toggle();
			if (name.equals("Chuyên mục"))
			{
				fragment = new category_fragment();
				manager.beginTransaction().replace(R.id.content_frame, fragment).commit();
			}
			else if (name.equals("Tác giả"))
			{
				fragment = new authors_fragment();
				manager.beginTransaction().replace(R.id.content_frame, fragment).commit();			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if ( menu.isMenuShowing()) {
			menu.toggle();
        }
        else {
            super.onBackPressed();
        }
	}
//	@Override
//		public boolean onOptionsItemSelected(android.view.MenuItem item) {
//			// TODO Auto-generated method stub
//			switch (item.getItemId()) {
//			case android.R.id.home:
//				menu.toggle();
//				return true;
//			}
//			return super.onOptionsItemSelected(item);
//		}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			Log.v("click option menu","11111");
			menu.toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public List<Item> getListMenuItem()
	{
		List<Item> items = new ArrayList<Item>();
		String category = getResources().getString(R.string.category_title);
		String[] list_category_array  = getResources().getStringArray(R.array.list_category_array);
		final Integer[] Icons_category = {
				R.drawable.ic_menu_category,
				R.drawable.ic_menu_people,
				R.drawable.ic_menu_top,
				R.drawable.ic_menu_new,
				R.drawable.ic_menu_random,
				R.drawable.ic_menu_contact

				};
		//Start to add category to list view navigation
		items.add(new Header(category));
		list_content_menu.add(category);
		
		for (int i =0; i< list_category_array.length; i++)
		{
			items.add(new ListItem(list_category_array[i], Icons_category[i], getApplicationContext()));
			list_content_menu.add(list_category_array[i]);
		}
		
		String setting = getResources().getString(R.string.setting_title);
		String[] list_setting_array  = getResources().getStringArray(R.array.list_setting_array);
		final Integer[] Icons_setting = {
				R.drawable.ic_menu_setting,
				R.drawable.ic_menu_delete

				};
		//Start to add setting to list view navigation
		items.add(new Header(setting));
		list_content_menu.add(setting);
		for (int i =0; i< list_setting_array.length; i++)
		{
		items.add(new ListItem(list_setting_array[i], Icons_setting[i], getApplicationContext()));
		list_content_menu.add(list_setting_array[i]);
		}
		return items;
	}
	
	
}
