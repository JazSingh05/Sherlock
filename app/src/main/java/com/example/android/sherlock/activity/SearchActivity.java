package com.example.android.sherlock.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sherlock.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abbin_j19pde on 1/24/2018.
 */

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    @BindView(R.id.searchBar)
    TextView searchBar;
    @BindView(R.id.searchButton)
    ImageButton searchButton;
    @BindView(R.id.settingsButton)
    ImageButton settingsButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        ButterKnife.bind(this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDatabase();
            }
        });
        try {
            ActionBar ab = getActionBar();
            if(ab != null) {
                ab.show();
            }else {
                Log.d(TAG, "No action bar");
            }
        }catch (NullPointerException npe) {
            Log.e(TAG, npe.getMessage());
        }

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, Settings.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        searchButton.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY));
    }

    public void searchDatabase() {
        if(!searchBar.getText().toString().matches("") || searchBar.getText().toString().matches("")) {
            String databaseSearch = searchBar.getText().toString();
            Log.d("CHECKING STRING", "The string you entered: " + databaseSearch);
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("SEARCH_TERM", databaseSearch);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Enter in Something", Toast.LENGTH_SHORT).show();
        }
    }
}
