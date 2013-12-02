package com.example.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RestClient {

	public static final String BASE_URL = "http://phatam.com/rest/public/index.php/";

	private static AsyncHttpClient client = new AsyncHttpClient();

	private static String getAbsoluteUrl(String relativeUrl) {
		return BASE_URL + relativeUrl;
	}

	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		Log.v("array received", "" + getAbsoluteUrl(url) + "==="+ params);
		client.get(getAbsoluteUrl(url), params, responseHandler);
	}

	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {

		client.setTimeout(100000);
		client.post(getAbsoluteUrl(url), params, responseHandler);
	}
	
	
	
}