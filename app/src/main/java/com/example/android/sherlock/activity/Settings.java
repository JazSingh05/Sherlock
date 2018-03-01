package com.example.android.sherlock.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.sherlock.R;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }


    public void mainScreen(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
