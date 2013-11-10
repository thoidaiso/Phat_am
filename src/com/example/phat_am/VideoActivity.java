package com.example.phat_am;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.actionbarsherlock.app.SherlockActivity;

public class VideoActivity extends SherlockActivity{
	
	String html = "<iframe class=\"youtube-player\" style=\"border: 0; width: 100%; height: 95%; padding:0px; margin:0px\" id=\"ytplayer\" type=\"text/html\" src=\"http://www.youtube.com/embed/msw4ktrSWRw"
            + "?fs=0\" frameborder=\"0\">\n"
            + "</iframe>";
	
	WebView wv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_activity);
		wv = (WebView)findViewById(R.id.webView); 
		wv.getSettings().setPluginState(PluginState.ON);
	    wv.getSettings().setJavaScriptEnabled(true);
	    wv.setWebChromeClient(new WebChromeClient());
	    wv.loadUrl("http://www.youtube.com/embed/msw4ktrSWRw?autoplay=1&vq=small");
//	        wv.loadDataWithBaseURL("", html , "text/html",  "UTF-8", "");

	}

}
