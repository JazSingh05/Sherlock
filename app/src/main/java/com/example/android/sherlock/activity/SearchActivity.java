package com.example.android.sherlock.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    @BindView(R.id.searchBar)
    TextView searchBar;
    @BindView(R.id.searchButton)
    ImageButton searchButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ButterKnife.bind(this);
        searchButton.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY));
    }
    public void searchDatabase(View view)
    {
        
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
