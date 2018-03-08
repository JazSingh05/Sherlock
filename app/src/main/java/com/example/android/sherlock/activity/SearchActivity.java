package com.example.android.sherlock.activity;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.sherlock.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abbin_j19pde on 1/24/2018.
 */

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.searchBar)
    TextView searchBar;
    @BindView(R.id.SettingsBut)
    ImageButton settings;
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
        settings.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY));
        searchButton.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY));
    }
    public void searchDatabase(View view)
    {
        
        if(!searchBar.getText().toString().matches("") || searchBar.getText().toString().matches("")) {
            String DatabaseSearch = searchBar.getText().toString();
            Log.d("CHECKING STRING", "The string you entered: " + DatabaseSearch);
            Intent intent = new Intent(this, SearchResultActivity.class);
            intent.putExtra("SEARCH_TERM", DatabaseSearch);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Enter in Something", Toast.LENGTH_SHORT).show();
        }


    }

    public void settingsMainShow(View view){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }



}
