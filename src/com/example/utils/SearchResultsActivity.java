package com.example.utils;


import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.SherlockActivity;

public class SearchResultsActivity extends SherlockActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }
}
