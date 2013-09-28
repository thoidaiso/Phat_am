package com.example.phat_am;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.support.v4.view.ViewPager;
public class MainActivity extends BaseActivity {

	public MainActivity() {
		super(R.string.title_bar_slide);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_frame);
//		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SampleListFragment()).commit();
		setSlidingActionBarEnabled(true);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
