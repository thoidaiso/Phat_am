package loadmore_list;


import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockFragment;
import com.example.listview.CategoryAdapter;
import com.example.listview.ListVideoAdapter;
import com.example.listview.Model_Category;
import com.example.listview.Model_Video;
import com.example.phat_am.R;
import com.example.showlistcontent.VideoInfoActivity;
import com.example.utils.Helper;

public class loadmore_video_fragment extends SherlockFragment{

	public ArrayList<Model_Video> list_model = new ArrayList<Model_Video>();
	ListView list;
	LoadmoreAdapter adapter = null;
	static String type;//top,random,new
	static String order; //order by date,title,rating
	static int limit = 8; //limit video will be show
	
	public loadmore_video_fragment()
	{
		this.type = "new";
		this.order = "rating";
		Log.v("video fragment type", ""+type);
		Log.v("order", ""+order);
	}
	public loadmore_video_fragment(String type, String order) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.order = order;
		Log.v("video fragment type", ""+type);
		Log.v("order", ""+order);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.list, container, false);
		
		setRetainInstance(true);
		String[] list_video = getResources().getStringArray(R.array.detail_video_array);
		String[] list_author = getResources().getStringArray(R.array.detail_video_author_array);
		
		Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.drawable.image);
		list_model.clear();
		for(int i=0; i<15; i++)
		{
			list_model.add(new Model_Video(bm, list_video[i], list_author[i]));
		}
		
		ListView list = (ListView)rootView.findViewById(R.id.list_view);
		if (adapter ==null)
		{
			Log.v("Add Info","====1");
			adapter = new LoadmoreAdapter(getSherlockActivity(), list_model, limit);
		}
		else
		{
			adapter.startProgressAnimation();
		}
		Log.v("Set adapter","");
		list.setAdapter(adapter);
		list.setOnItemClickListener(OnItemClick);
//		Helper.getListViewSize(list);
		return rootView;
	}
	
	private OnItemClickListener OnItemClick = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			Log.v("u click",list_model.get(arg2).getName().toString());
			Intent i = new Intent(getSherlockActivity(), VideoInfoActivity.class);
			i.putExtra("link", list_model.get(arg2).getName().toString());
            startActivity(i);
		}
	};
	
		
}
