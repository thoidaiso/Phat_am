package loadmore_list;

/***
  Copyright (c) 2008-2009 CommonsWare, LLC
  Portions (c) 2009 Google, Inc.
  
  Licensed under the Apache License, Version 2.0 (the "License"); you may
  not use this file except in compliance with the License. You may obtain
  a copy of the License at
    http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/    


import java.util.ArrayList;

import com.example.listview.Model_Video;
import com.example.phat_am.R;

import android.app.Activity;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Adapter that delegates to a wrapped LisAdapter, much as
 * CursorWrapper delegates to a wrapped Cursor.
 */
public class AdapterWrapper extends ArrayAdapter<Model_Video> {
//  private ListAdapter wrapped=null;
	private ArrayAdapter<Model_Video> wrapped = null;

  /**
    * Constructor wrapping a supplied ListAdapter
    */
//  public AdapterWrapper(ListAdapter wrapped) {
//    super();
//    
//    this.wrapped=wrapped;
//    
//    wrapped.registerDataSetObserver(new DataSetObserver() {
//      public void onChanged() {
//        notifyDataSetChanged();
//      }
//      
//      public void onInvalidated() {
//        notifyDataSetInvalidated();
//      }
//    });
//  }
  public AdapterWrapper(Activity context, int layout, ArrayList<Model_Video> list, ArrayAdapter<Model_Video> wrapped) {
	  super(context, R.layout.loadmore_video_list_item, list);
	  this.wrapped=wrapped;
	  
	  wrapped.registerDataSetObserver(new DataSetObserver() {
	    @Override
		public void onChanged() {
	      notifyDataSetChanged();
	    }
	    
	    @Override
		public void onInvalidated() {
	      notifyDataSetInvalidated();
	    }
	  });
}
  
//  public AdapterWrapper(Activity context, int layout, ArrayList<Model_Video> list) {
//	  super(context, R.layout.loadmore_video_list_item, list);
//	  wrapped = new ArrayAdapter<Model_Video>(context, R.layout.loadmore_video_list_item, list);
////	    this.wrapped=wrapped;
//	    
//	    wrapped.registerDataSetObserver(new DataSetObserver() {
//	      public void onChanged() {
//	        notifyDataSetChanged();
//	      }
//	      
//	      public void onInvalidated() {
//	        notifyDataSetInvalidated();
//	      }
//	    });
//	  }

  /**
    * Get the data item associated with the specified
    * position in the data set.
    * @param position Position of the item whose data we want
    */
  @Override
  public Model_Video getItem(int position) {
    return (wrapped.getItem(position));
  }

  /**
    * How many items are in the data set represented by this
    * Adapter.
    */
  @Override
  public int getCount() {
	  Log.v("get wrapper count","");
    return(wrapped.getCount());
  }

  /**
    * Returns the number of types of Views that will be
    * created by getView().
    */
  @Override
  public int getViewTypeCount() {
	  Log.v("get getViewTypeCount wraper","");
    return(wrapped.getViewTypeCount());
  }

  /**
    * Get the type of View that will be created by getView()
    * for the specified item.
    * @param position Position of the item whose data we want
    */
  @Override
  public int getItemViewType(int position) {
    return(wrapped.getItemViewType(position));
  }

  /**
    * Are all items in this ListAdapter enabled? If yes it
    * means all items are selectable and clickable.
    */
  @Override
  public boolean areAllItemsEnabled() {
    return(wrapped.areAllItemsEnabled());
  }

  /**
    * Returns true if the item at the specified position is
    * something selectable.
    * @param position Position of the item whose data we want
    */
  @Override
  public boolean isEnabled(int position) {
    return(wrapped.isEnabled(position));
  }

  /**
    * Get a View that displays the data at the specified
    * position in the data set.
    * @param position Position of the item whose data we want
    * @param convertView View to recycle, if not null
    * @param parent ViewGroup containing the returned View
    */
  @Override
  public View getView(int position, View convertView,
                      ViewGroup parent) {
    return(wrapped.getView(position, convertView, parent));
  }

  /**
    * Get the row id associated with the specified position
    * in the list.
    * @param position Position of the item whose data we want
    */
  @Override
  public long getItemId(int position) {
    return(wrapped.getItemId(position));
  }
  
  /**
    * Returns the ListAdapter that is wrapped by the endless
    * logic.
    */
  protected ArrayAdapter<Model_Video> getWrappedAdapter() {
    return(wrapped);
  }
}