package com.example.android.sherlock;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by abbin_j19pde on 1/24/2018.
 */

public class DatabaseResult extends AppCompatActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_search);

        Intent in = getIntent();

        Bundle bundle = in.getExtras();
        String query = bundle.getString("sherlock.com.sherlock");

        Database db = new Database(this);
        ArrayList<String> queries = new ArrayList<String>();
        queries = db.searchQuery(query);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mylinearlayout);

        for(int i = 0; i < 7; i++) {
            TextView textView = new TextView(this);
            textView.setText(queries.get(i));
            linearLayout.addView(textView);
        }



    }



}
