package com.example.android.sherlock.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.android.sherlock.R;
import com.example.android.sherlock.adapter.RecyclerAdapter;
import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abbin_j19pde and stephen on 1/24/2018.
 */

public class SearchResultActivity extends AppCompatActivity implements RecyclerAdapter.CartListener {
    private static final String TAG = "SearchResultActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recycler;
    private RecyclerAdapter adapter;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private List<Item> items;
    private Comparator<Item> priceCompare;
    private Comparator<Item> distanceCompare;
    private Map<Long, Double> distanceMap = new HashMap<>();
    private Map<Long, Store> storeMap;
    private TextView cartCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_search);
        Intent in = getIntent();
        Bundle bundle = in.getExtras();
        Toolbar tb = findViewById(R.id.toolbar);
        cartCount = tb.findViewById(R.id.cartCount);
        setSupportActionBar(tb);
        try {
            String query = bundle.getString("SEARCH_TERM");
            Database db = new Database(this);
            items = db.searchItems(query);
            storeMap = db.getStoresAsMapById();

        }catch (NullPointerException e) {
            Log.e(TAG, e.getMessage());
        }
        Random r = new Random();
        for(Long l: storeMap.keySet())
            distanceMap.put(l, r.nextDouble()*10);
        this.priceCompare = new PriceComparator();
        this.distanceCompare = new DistanceComparator(distanceMap);
        adapter = new RecyclerAdapter(this, items, storeMap, priceCompare, distanceMap, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tabLayout.getSelectedTabPosition();
                RecyclerAdapter oldAdapter = (RecyclerAdapter) recycler.getAdapter();
                RecyclerAdapter adapt;
                if(pos == 0) {
                    adapt = new RecyclerAdapter(SearchResultActivity.this, items, storeMap, priceCompare, distanceMap, oldAdapter.getCartContents());
                } else {
                    adapt = new RecyclerAdapter(SearchResultActivity.this, items, storeMap, distanceCompare, distanceMap, oldAdapter.getCartContents());
                }
                recycler.setAdapter(adapt);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //not implemented
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //not implemented
            }
        });
        recycler.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);
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
            double delta = distanceMap.get(o1.getStoreId()) - distanceMap.get(o2.getStoreId());
            if (delta < 0) return -1;
            if (delta > 0) return 1;
            return 0;
        }
    }

    @Override
    public void onItemAddToCart() {
        int count = ((RecyclerAdapter) recycler.getAdapter()).getCartContents().size();
        this.cartCount.setText(String.format(Locale.US, "%d", count));
    }
}
