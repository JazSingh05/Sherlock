package com.example.android.sherlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.R;

import java.util.List;

/**
 * Created by abbin_j19pde on 1/24/2018.
 */

public class SearchResultActivity extends AppCompatActivity {
    private static final String TAG = "SearchResultActivity";

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_search);

        Intent in = getIntent();

        Bundle bundle = in.getExtras();
        try {
            String query = bundle.getString("SEARCH_TERM");

            Database db = new Database(this);
            List<Item> queries = db.searchItems(query);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mylinearlayout);

            for(Item i: queries) {
                TextView textView = new TextView(this);
                textView.setText(i.getName());
                linearLayout.addView(textView);
            }
        }catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }
    }



}
