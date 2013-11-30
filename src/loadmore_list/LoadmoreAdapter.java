package loadmore_list;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import com.example.listview.Model_Video;
import com.example.phat_am.R;

class LoadmoreAdapter extends EndlessAdapter {
  private RotateAnimation rotate=null;
  private View pendingView=null;
  
  private final Activity context;
  private  ArrayList<Model_Video> list = new ArrayList<Model_Video>();
  private  ArrayList<Model_Video> new_list = new ArrayList<Model_Video>();
  private int count = 10;
  static class ViewHolder {
    public TextView name;
    public TextView author;
    public ImageView image;
  }
  
  public LoadmoreAdapter(Activity context, ArrayList<Model_Video> list, int count) {
		// TODO Auto-generated constructor stub
	    super(context, R.layout.loadmore_video_list_item, list, new ArrayAdapter<Model_Video>(context, 
	    		R.layout.loadmore_video_list_item,
			    R.id.loadmore_list_visible_content,
			    list));
//		super(context, R.layout.loadmore_video_list_item, list);
		this.context = context;
		this.list = list;
		if (count !=0)
		{
			this.count = count;
		}
		rotate=new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
		rotate.setDuration(600);
		rotate.setRepeatMode(Animation.RESTART);
		rotate.setRepeatCount(Animation.INFINITE);
	}
  
//  LoadmoreAdapter(Context ctxt, ArrayList<Integer> list) {
//    super(new ArrayAdapter<Integer>(ctxt,
//                                    R.layout.loadmore_video_list_item,
//                                    android.R.id.text1,
//                                    list));
//    rotate=new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
//                                0.5f, Animation.RELATIVE_TO_SELF,
//                                0.5f);
//    rotate.setDuration(600);
//    rotate.setRepeatMode(Animation.RESTART);
//    rotate.setRepeatCount(Animation.INFINITE);
//  }
  
  public View getView(int position, View convertView, ViewGroup parent) {
	  Log.v("getView position", ""+position);
	  Log.v("getView count", ""+super.getCount());
	  if (position < super.getCount()-1) 
	  {
		    View rowView=convertView;
			ViewHolder viewHolder;
			if(rowView == null)
			{
				LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				rowView=inflater.inflate(R.layout.loadmore_video_list_item, null);
				viewHolder = new ViewHolder();
				viewHolder.name = (TextView) rowView.findViewById(R.id.video_list_title);
				viewHolder.author = (TextView) rowView.findViewById(R.id.video_list_astist);
				viewHolder.image = (ImageView) rowView.findViewById(R.id.video_list_image);
				rowView.setTag(viewHolder);
			  } 
			viewHolder=(ViewHolder)rowView.getTag();
//			Log.v("get view of video", list.get(position).getName());
			viewHolder.name.setText(list.get(position).getName().toString());
//			Log.v("get view of video", "4");
			viewHolder.author.setText("Tác giả: "+ list.get(position).getAuthor().toString());
//			Log.v("get view of video", "5");
			viewHolder.image.setImageBitmap(list.get(position).getImage());
//			Log.v("get view of video", "6");
			if (position %2 ==0)
			{
				rowView.setBackgroundColor(context.getResources().getColor(R.color.list_row_green));
			}
			else
			{
				rowView.setBackgroundColor(context.getResources().getColor(R.color.white));
			}
			return rowView;
	  }
	  return(super.getView(position, convertView, parent));
	  
  }
  
  @Override
  protected View getPendingView(ViewGroup parent) {
    View row=LayoutInflater.from(parent.getContext()).inflate(R.layout.loadmore_video_list_item, null);
    
    pendingView=row.findViewById(R.id.loadmore_list_visible_content);
    pendingView.setVisibility(View.GONE);
    pendingView=row.findViewById(R.id.throbber);
    pendingView.setVisibility(View.VISIBLE);
    startProgressAnimation();
    
    return(row);
  }
  
  @Override
  protected boolean cacheInBackground() {
    SystemClock.sleep(10000);       // pretend to do work
    String[] list_video = context.getResources().getStringArray(R.array.detail_video_array);
	String[] list_author = context.getResources().getStringArray(R.array.detail_video_author_array);
	Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.image);
	new_list.clear();
	for(int i=0; i<15; i++)
	{
		new_list.add(new Model_Video(bm, list_video[i], list_author[i]));
	}
    return(getWrappedAdapter().getCount()<75);
  }
  
  @Override
  protected void appendCachedData() {
	 Log.v("appendCachedData","======");
    if (getWrappedAdapter().getCount()<75) {
      @SuppressWarnings("unchecked")
//      ArrayAdapter<Integer> a=(ArrayAdapter<Integer>)getWrappedAdapter();
      ArrayAdapter<Model_Video> a = (ArrayAdapter<Model_Video>) getWrappedAdapter();
      
      for (int i=0;i<15;i++) 
      { 
    	  a.add(new_list.get(i));
      }
    }
  }
  
  void startProgressAnimation() {
    if (pendingView!=null) {
      pendingView.startAnimation(rotate);
    }
  }
}