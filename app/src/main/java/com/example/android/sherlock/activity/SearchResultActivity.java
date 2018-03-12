package com.example.android.sherlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.android.sherlock.adapter.RecyclerAdapter;
import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.R;
import com.example.android.sherlock.model.Store;

import java.util.Comparator;
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
    private RecyclerAdapter adapter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private Comparator<Item> priceCompare;
    private Comparator<Item> distanceCompare;

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
            this.priceCompare = new PriceComparator();
            this.distanceCompare = new DistanceComparator(adapter.getDistanceMap());
        }catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tabLayout.getSelectedTabPosition();
                if(pos == 0) adapter.sort(priceCompare);
                else adapter.sort(distanceCompare);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
    }

    private class PriceComparator implements Comparator<Item> {

        @Override
        public int compare(Item o1, Item o2) {
            if ((o1.getPrice() - o2.getPrice())< 0) return -1;
            if ((o1.getPrice() - o2.getPrice()) > 0) return 1;
            return 0;
        }
    }

    private class DistanceComparator implements Comparator<Item> {
        private Map<Long, Double> distanceMap;
        private DistanceComparator(Map<Long, Double> dmap){
            this.distanceMap = dmap;
        }

        @Override
        public int compare(Item o1, Item o2) {
            double delta = distanceMap.get(o1.getId()) - distanceMap.get(o2.getId());
            if (delta < 0) return -1;
            if (delta > 0) return 1;
            return 0;
        }
    }


}
