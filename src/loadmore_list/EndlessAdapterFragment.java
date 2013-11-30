//package loadmore_list;
//
//import android.app.ListFragment;
//import android.os.Bundle;
//import java.util.ArrayList;
//
//public class EndlessAdapterFragment extends ListFragment {
//  LoadmoreAdapter adapter=null;
//  
//  @Override
//  public void onActivityCreated(Bundle savedInstanceState) {
//    super.onActivityCreated(savedInstanceState);
//
//    setRetainInstance(true);
//    
//    if (adapter==null) {
//      ArrayList<Integer> items=new ArrayList<Integer>();
//      
//      for (int i=0;i<25;i++) { items.add(i); }
//      
//      adapter=new LoadmoreAdapter(getActivity(), items);
//    }
//    else {
//      adapter.startProgressAnimation();
//    }
//    
//    setListAdapter(adapter);
//  }
//}
