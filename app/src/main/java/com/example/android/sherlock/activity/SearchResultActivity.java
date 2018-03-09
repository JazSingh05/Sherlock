package com.example.android.sherlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.sherlock.adapter.RecyclerAdapter;
import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.R;
import com.example.android.sherlock.model.Store;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abbin_j19pde and stephen on 1/24/2018.
 */

public class SearchResultActivity extends AppCompatActivity {
    private static final String TAG = "SearchResultActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recycler;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_search);

        Intent in = getIntent();

        Bundle bundle = in.getExtras();
        try {
            String query = bundle.getString("SEARCH_TERM");

            Database db = new Database(this);
            List<Item> items = db.searchItems(query);
            Map<Long, Store> storeMap = db.getStoresAsMapById();

            adapter = new RecyclerAdapter(this, items, storeMap);
        }catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }


}
